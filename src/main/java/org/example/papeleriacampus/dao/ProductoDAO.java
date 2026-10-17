package org.example.papeleriacampus.dao;

import org.example.papeleriacampus.database.DatabaseConnection;
import org.example.papeleriacampus.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductoDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductoDAO.class.getName());

    public int guardarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto(codigo,nombre,categoria,precio,stock) VALUES (?,?,?,?,?)";
        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarCampos(ps, producto);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public int actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE producto SET codigo=?, nombre=?, categoria=?, precio=?, stock=? WHERE id_producto=?";
        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            asignarCampos(ps, producto);
            ps.setInt(6, producto.getIdProducto());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    private void asignarCampos(PreparedStatement ps, Producto producto) throws SQLException {
        ps.setString(1, producto.getCodigo());
        ps.setString(2, producto.getNombre());
        ps.setString(3, producto.getCategoria());
        ps.setBigDecimal(4, producto.getPrecio());
        ps.setInt(5, producto.getStock());
    }
}
