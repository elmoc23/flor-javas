import java.sql.*;
import java.util.Scanner;

public class stockflor {

    public static void main(String[] args) {
        try {

            //establese la conexion
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flores", "root", "12345");
            Statement statement = connection.createStatement();
            // utilizamos este metodo para preguntar los que se quiere busacar
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre de la flor: ");
            String florbuscar = scanner.nextLine();
            // con el preparedsatatement consultamos en la columna de UnidadVenta
            PreparedStatement searchStatement = connection.prepareStatement("SELECT * FROM flor WHERE NombreFlor = ?");
            searchStatement.setString(1, florbuscar);
            ResultSet resultSet = searchStatement.executeQuery();
            System.out.println("el stock de la flor es :");
            // Mostrar los resultados
            while (resultSet.next()) {

                String nombreflor = resultSet.getString("NombreFlor");
                String stockdeflor = resultSet.getString("PrecioAct");


                System.out.println(" | | "  + "  " + nombreflor + " tiene en el stock : " + stockdeflor + " | | "  );
            }

            // Cerrar las conexiones y los recursos
            resultSet.close();
            searchStatement.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
