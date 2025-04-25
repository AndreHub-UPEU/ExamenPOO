package pe.edu.upeu.examenunidad1.servicios;

import pe.edu.upeu.examenunidad1.modelo.examenUnidad1TO;

import java.util.List;

public interface examenUnidad1ServiciosI {

    //C = CREATE (CREAR)
    public void save(examenUnidad1TO to);

    //R = READ (LEER)
    public List<examenUnidad1TO> findAll();
    public examenUnidad1TO findById(int index);

    //U = UPDATE (ACTUALIZAR)
    public void update(examenUnidad1TO to, int index);

    //D = DELETE (ELIMINAR)
    public void delete(examenUnidad1TO to);
    public void deleteById(int index);
}
