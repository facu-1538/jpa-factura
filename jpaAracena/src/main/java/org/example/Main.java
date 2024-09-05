package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha facu");

        try {
            entityManager.getTransaction().begin();

            Cliente cliente = Cliente.builder()
                    .nombre("Facundo")
                    .apellido("Aracena")
                    .dni(51710384)
                    .build();

            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("San Martin")
                    .numero(123)
                    .build();

            Factura factura = Factura.builder()
                    .fecha("12 de Octubre")
                    .numero(2645)
                    .total(600)
                    .build();

            DetalleFactura detalle1 = DetalleFactura.builder()
                    .cantidad(5)
                    .subtotal(100)
                    .build();

            Articulo articulo1 = Articulo.builder()
                    .cantidad(15)
                    .denominacion("Chocolatada")
                    .precio(20)
                    .build();

            Articulo articulo2 = Articulo.builder()
                    .cantidad(20)
                    .denominacion("Detegente")
                    .precio(50)
                    .build();

            DetalleFactura detalle2 = DetalleFactura.builder()
                    .cantidad(100)
                    .subtotal(500)
                    .build();

            Categoria Liquido = Categoria.builder()
                    .denominacion("Liquido")
                    .build();
            Categoria Comestible = Categoria.builder()
                    .denominacion("Comestible")
                    .build();

            Categoria Limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();


            //las relaciones

            cliente.setDomicilio(domicilio);

            factura.setCliente(cliente);
            factura.addDetalleFactura(detalle1);
            factura.addDetalleFactura(detalle2);

            articulo1.getCategorias().add(Liquido);
            articulo1.getCategorias().add(Comestible);

            articulo2.getCategorias().add(Liquido);
            articulo2.getCategorias().add(Limpieza);

            detalle2.setArticulo(articulo2);
            detalle1.setArticulo(articulo1);


            //los persist

            entityManager.persist(domicilio);
            entityManager.persist(cliente);
            entityManager.persist(factura);
            entityManager.persist(detalle2);
            entityManager.persist(detalle1);
            entityManager.persist(articulo1);
            entityManager.persist(articulo2);
            entityManager.persist(Liquido);
            entityManager.persist(Limpieza);
            entityManager.persist(Comestible);


            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception

            System.out.println("holaeee");


            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
