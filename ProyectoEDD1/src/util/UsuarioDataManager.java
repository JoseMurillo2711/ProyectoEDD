/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import TDA.ArrayList;
import TDA.List;
import modelo.Usuario;

import java.io.*;

import javafx.application.Platform;
import modelo.Vehiculo;
import static util.CONSTANTES.MENSAJE_ERROR;
import static util.CONSTANTES.USUARIOS_FILE;

/**
 *
 * @autor Michelle
 */
public class UsuarioDataManager {

    private static UsuarioDataManager instance;

    private List<Usuario> usuarios;

    private Usuario usuarioActual;

    private UsuarioDataManager() {
        usuarios = leerArchivoUsuarios();
        usuarioActual = null;
    }

    public static UsuarioDataManager getInstance() {
        if (instance == null) {
            instance = new UsuarioDataManager();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        escribirArchivoUsuarios();
    }

    private List<Usuario> leerArchivoUsuarios() {
        File archivo = new File(USUARIOS_FILE);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) obj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void escribirArchivoUsuarios() {
        File archivo = new File(USUARIOS_FILE);
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
            try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(archivo))) {
                obj.writeObject(this.usuarios);
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error " + e.getMessage());
                Platform.runLater(() -> Alertas.alertaError(MENSAJE_ERROR, e.getMessage() + " " + e.getLocalizedMessage()));
            }
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error " + e.getMessage());
            Platform.runLater(() -> Alertas.alertaError(MENSAJE_ERROR, e.getMessage()));
        }
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario user : this.usuarios) {
            if (user.getNickname().equals(nombreUsuario) && user.getPassword().equals(contrasena)) {
                usuarioActual = user;
                return true;
            }
        }
        return false;
    }

    public void eliminarAutoFavorito(Vehiculo vehiculo) throws Exception {
        if (usuarioActual != null) {
            usuarioActual.getVehiculosFavoritos().remove(vehiculo);
            this.usuarios.set(obtenerIndice(), usuarioActual);
            escribirArchivoUsuarios();
        }
        throw new Exception("Debe iniciar sesion");
    }

    private int obtenerIndice() {
        int ind = this.usuarios.indexOf(usuarioActual);
        return ind;
    }

    public void agregarAutoFavorito(Vehiculo vehiculo) throws Exception {
        if (usuarioActual != null) {
            usuarioActual.getVehiculosFavoritos().addLast(vehiculo);
            this.usuarios.set(obtenerIndice(), usuarioActual);
            escribirArchivoUsuarios();
        }
        throw new Exception("Debe iniciar sesion");
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws Exception {
        for (Usuario user : this.usuarios) {
            if (user.getNickname().toLowerCase().equals(nuevoUsuario.getNickname().toLowerCase())) {
                throw new Exception("El nombre de usuario ya existe");
            }
        }
        this.usuarios.addLast(nuevoUsuario);
        escribirArchivoUsuarios();
    }

    protected void editarVehiculo(Vehiculo vehiculo) throws Exception {
        for (Usuario user : this.usuarios) {
            for (Vehiculo v : user.getVehiculosFavoritos()) {
                if (v.equals(vehiculo)) {
                    int ind = user.getVehiculosFavoritos().indexOf(vehiculo);
                    user.getVehiculosFavoritos().set(ind, vehiculo);
                }
            }
            escribirArchivoUsuarios();
        }
    }
    
    protected void elimimarVehiculo(Vehiculo vehiculo) throws Exception {
        for (Usuario user : this.usuarios) {
            for (Vehiculo v : user.getVehiculosFavoritos()) {
                if (v.equals(vehiculo)) {                    
                    user.getVehiculosFavoritos().remove(vehiculo);
                }
            }
            escribirArchivoUsuarios();
        }
    }
    
}
