package pe.edu.upeu.examenunidad1.servicios;

import org.springframework.stereotype.Service;
import pe.edu.upeu.examenunidad1.modelo.examenUnidad1TO;

import java.util.ArrayList;
import java.util.List;

@Service
public class examenUnidad1ServiciosImp implements examenUnidad1ServiciosI {

    List<examenUnidad1TO> datos= new ArrayList<>();

    //C = CREATE (CREAR)
    @Override
    public void save(examenUnidad1TO to) {
        datos.add(to);
    }

    //R = READ (LEER)
    @Override
    public List<examenUnidad1TO> findAll() {
        return datos;
    }

    //R = READ (LEER)
    @Override
    public examenUnidad1TO findById(int index) {
        return datos.get(index);
    }

    //U = UPDATE (ACTUALIZAR)
    @Override
    public void update(examenUnidad1TO to, int index) {
        datos.set(index, to);
        //actualizar datos(0,1000)
        //array datos("el lugar que yo elija")("100") = datos.set(0,100)
        //datos(0,100)

    }

    //D = DELETE (ELIMINAR)
    @Override
    public void delete(examenUnidad1TO to) {
        datos.remove(to);
        //eliminar datos[juan,pedro,mateo,lucas]
        //datos.remove(juan)
        //datos[andre,jhon,fernando]
    }

    //D = DELETE (ELIMINAR)
    @Override
    public void deleteById(int index) {
        datos.remove(index);
        //         datos[ 0     1      2      3]
        //eliminar datos[juan,pedro,mateo,lucas]
        //datos.remove(0)
        //datos[andre,jhon,fernando]
    }
}
