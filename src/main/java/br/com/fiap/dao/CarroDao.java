package br.com.fiap.dao;

import br.com.fiap.classes.Carro;
import br.com.fiap.factory.DbConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDao {

    // Método para listar os carros:
    public List<Carro> listar() throws SQLException {
        List<Carro> carros = new ArrayList<>();

        // Abre a conecção com o banco de dados:
        Connection connection = DbConnectionFactory.createConnection();
        PreparedStatement preparedStatement =  connection.prepareStatement("SELECT *  FROM carros");

        ResultSet resultSet =  preparedStatement.executeQuery();

        while (resultSet.next()){
            Carro carro = new Carro(resultSet.getInt("id"), resultSet.getInt("ano"), resultSet.getString("modelo"));
            carros.add(carro);
        }

        connection.close();
        return carros;
    };


    // Encontrar um carro específico dentro da lista:
    public Carro encontrar(int carroId) throws SQLException {

        // Abre a conecção com o banco de dados:
        Connection connection = DbConnectionFactory.createConnection();
        PreparedStatement preparedStatement =  connection.prepareStatement(
                "SELECT *  FROM carros WHERE id = ?");

        preparedStatement.setInt(1, carroId);
        ResultSet resultSet =  preparedStatement.executeQuery();

        Carro carro = null;
        if (resultSet.next()){
          carro = new Carro(resultSet.getInt("id"), resultSet.getInt("ano"), resultSet.getString("modelo"));

        }


        connection.close();
        return carro;
    }

    // Inserir um carro novo na lista:
    public Carro inserir(Carro carro) throws SQLException{
        Connection connection = DbConnectionFactory.createConnection();

        // INSERT INTO carros (ano, modelo) values (2000, 'Ka');

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO carros (ano, modelo) values (?, ?)", new String[] {"id"});

        statement.setInt(1, carro.ano);
        statement.setString(2, carro.modelo);

       ResultSet resultSet =  statement.executeQuery();
       if(resultSet.next()){
         carro.id =  resultSet.getInt(1);
       }else {
           System.out.println("Ops, algo de errado aconteceu no insert!");
       }

        connection.close();
        return carro;
    }

    public void atualizar(Carro carro) throws SQLException{

        Carro carroEncontrado = this.encontrar(carro.id);

        if(carroEncontrado == null){
            System.out.println("Carro não encontrado!");
            return;

        }else {
            Connection connection = DbConnectionFactory.createConnection();

            // INSERT INTO carros (ano, modelo) values (2000, 'Ka');
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE carros SET ano = ?, modelo = ? WHERE id = ?");

            statement.setInt(1, carro.ano);
            statement.setString(2, carro.modelo);
            statement.setInt(3, carro.id);

            int affectedRows =  statement.executeUpdate();

            if(affectedRows!= 1){
                System.out.println("Opss! Algo errado ocorreu no update!");
            }else {
                System.out.println("O carro foi atualizado com sucesso!");
            }

            connection.close();

        }
    }

    public void deletar(int carroId) throws SQLException{

        Carro carroEncontrado = this.encontrar(carroId);

        if(carroEncontrado == null){
            System.out.println("Carro não encontrado!");
            return;

        }else {
            Connection connection = DbConnectionFactory.createConnection();

            // INSERT INTO carros (ano, modelo) values (2000, 'Ka');
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM carros WHERE id = ?");

            statement.setInt(1, carroId);


            int affectedRows =  statement.executeUpdate();

            if(affectedRows!= 1){
                System.out.println("Opss! Algo errado ocorreu ao deletar o carro!");
            }else {
                System.out.println("O carro foi deletado com sucesso!");
            }

            connection.close();

        }
    }


}
