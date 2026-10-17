package org.example.papeleriacampus.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.papeleriacampus.dao.ProductoDAO;
import org.example.papeleriacampus.model.Producto;

import java.math.BigDecimal;

public class ProductosController {
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private TextField txtBusqueda;
    @FXML private ComboBox<String> cmbFiltroCategoria;
    @FXML private CheckBox chkExistencias;
    @FXML private Label lblSeleccion;
    @FXML private Label lblContador;
    @FXML private TableView<Producto> tblProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, BigDecimal> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private Integer idSeleccionado;

    @FXML
    public void initialize() {
        cmbCategoria.setItems(FXCollections.observableArrayList("Papelería", "Tecnología", "Accesorios"));
        cmbFiltroCategoria.setItems(FXCollections.observableArrayList("Todas", "Papelería", "Tecnología", "Accesorios"));
        cmbFiltroCategoria.setValue("Todas");
        configurarTableView();
        tblProductos.getSelectionModel().selectedItemProperty().addListener((observable, anterior, producto) -> {
            if (producto != null) cargarProductoEnFormulario(producto);
        });
    }

    private void configurarTableView() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdProducto()).asObject());
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colCategoria.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategoria()));
        colPrecio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrecio()));
        colStock.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStock()).asObject());
        colPrecio.setCellFactory(columna -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal precio, boolean vacio) {
                super.updateItem(precio, vacio);
                setText(vacio || precio == null ? null : "C$ " + precio.setScale(2).toPlainString());
            }
        });
        tblProductos.setItems(listaProductos);
        tblProductos.setPlaceholder(new Label("No hay productos para mostrar."));
    }

    private void cargarProductoEnFormulario(Producto producto) {
        idSeleccionado = producto.getIdProducto();
        txtCodigo.setText(producto.getCodigo());
        txtNombre.setText(producto.getNombre());
        cmbCategoria.setValue(producto.getCategoria());
        txtPrecio.setText(producto.getPrecio().toPlainString());
        txtStock.setText(String.valueOf(producto.getStock()));
    }

    private boolean cargarProductos(boolean avisarSinResultados) {
        limpiarFormulario();
        lblContador.setText("Registros visibles: " + listaProductos.size());
        if (avisarSinResultados && listaProductos.isEmpty()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sin coincidencias", "No se encontraron productos con los filtros indicados.");
        }
        return true;
    }

    @FXML
    private void nuevoProducto(){

    }

    @FXML
    private void guardarProducto() {

    }

    @FXML
    private void actualizarProducto() {

    }

    @FXML
    private void eliminarProducto() {

    }

    @FXML
    private void buscarProductos() {

    }

    @FXML
    private void recargarProductos() {

    }

    @FXML
    private void limpiarFiltros() {
        txtBusqueda.clear();
        cmbFiltroCategoria.setValue("Todas");
        chkExistencias.setSelected(false);
        cargarProductos(false);
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        tblProductos.getSelectionModel().clearSelection();
        lblSeleccion.setText("Nuevo producto");
        txtCodigo.clear();
        txtNombre.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        txtPrecio.clear();
        txtStock.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
