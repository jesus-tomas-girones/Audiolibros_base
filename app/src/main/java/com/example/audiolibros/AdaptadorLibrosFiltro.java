package com.example.audiolibros;

import android.content.Context;

import java.util.List;
import java.util.Vector;

public class AdaptadorLibrosFiltro extends AdaptadorLibros {
    private List<Libro> vectorSinFiltro;// Vector con todos los libros
    private List<Integer> indiceFiltro; // Índice en vectorSinFiltro de
    // Cada elemento de listaLibros
    private String busqueda = "";         // Búsqueda sobre autor o título
    private String genero = "";           // Género seleccionado
    private boolean novedad = false;      // Si queremos ver solo novedades
    private boolean leido = false;        // Si queremos ver solo leidos

    public AdaptadorLibrosFiltro(Context contexto,
                                 List<Libro> listaLibros) {
        super(contexto, listaLibros);
        vectorSinFiltro = listaLibros;
        recalculaFiltro();
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda.toLowerCase();
        recalculaFiltro();
    }

    public void setGenero(String genero) {
        this.genero = genero;
        recalculaFiltro();
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
        recalculaFiltro();
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
        recalculaFiltro();
    }

    public void recalculaFiltro() {
        listaLibros = new Vector<>();
        indiceFiltro = new Vector<>();
        for (int i = 0; i < vectorSinFiltro.size(); i++) {
            Libro libro = vectorSinFiltro.get(i);
            if ((libro.titulo.toLowerCase().contains(busqueda) ||
                    libro.autor.toLowerCase().contains(busqueda))
                    && (libro.genero.startsWith(genero))
                    && (!novedad || (novedad && libro.novedad))
                    && (!leido || (leido && libro.leido))) {
                listaLibros.add(libro);
                indiceFiltro.add(i);
            }
        }
    }

    public Libro getItem(int posicion) {
        return vectorSinFiltro.get(indiceFiltro.get(posicion));
    }

    public long getItemId(int posicion) {
        return indiceFiltro.get(posicion);
    }

    public void borrar(int posicion){
        vectorSinFiltro.remove((int)getItemId(posicion));
        recalculaFiltro();
    }

    public void insertar(Libro libro){
        vectorSinFiltro.add(libro);
        recalculaFiltro();
    }
}
