package br.com.fiap;

import br.com.fiap.classes.Carro;
import br.com.fiap.dao.CarroDao;

import java.sql.*;

public class App
{
    public static void main( String[] args ) throws SQLException {
        CarroDao carroDao = new CarroDao();


        // Criar carro novo
      // Carro carro =  carroDao.inserir(new Carro(2010, "Cross Fox"));

       // Exibir carros da lista
       /* for(Carro item: carroDao.listar()){
            System.out.println(item.modelo);
            System.out.println(item.id);
        }*/


       // carroDao.atualizar(carro);



        // Deletar carro:
        carroDao.deletar(26);
        // Exibir carros da lista
        for(Carro item: carroDao.listar()){
            System.out.println(item.modelo);
            System.out.println(item.id);
        }



    }
}
