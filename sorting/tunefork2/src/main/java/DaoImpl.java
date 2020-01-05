import java.sql.*;

public class DaoImpl extends Dao{

    public DaoImpl(Connection connection) {
        super(connection);
    }
    //Creating a new table in our DB
    public void create_table(String [] fields){
        // making a String of the fields of the table.
        String fieldString = "(";
        for (int i=0; i<fields.length;i++){
            fieldString = fieldString + fields[i]+"_field varchar";
            if (i == fields.length-1){
                fieldString = fieldString+")";
            }
            else {
                fieldString = fieldString+",";
            }
        }
        //creating the new table with the fields which given at the first line of the csv file
        try{
            startConnection();
            Statement stmt = connection.createStatement();
            String sql;
            sql = "CREATE TABLE IF Not EXISTS java_exam2 " + fieldString;
            System.out.println("java_exam2 table was created.");
            stmt.executeQuery(sql);
            closeConnection();

        }
        catch (SQLException se){
            //Handle errors for JDBC
           // se.printStackTrace();
        }
        catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }
public int create(String[] array){//insert a new record to the DB

        String values ="(";

        for (int i=0; i<array.length; i++){
            values =values+"?,";
        }
        int last =  array.length*2;
        values= values.substring(0,last);
        values= values+")";

    try {
        startConnection();

        String query = "INSERT INTO java_exam2 VALUES "+values;
        PreparedStatement insert = connection.prepareStatement(query);
        for (int i=0; i<array.length; i++) {
            insert.setString(i+1,array[i]);
        }


        insert.executeUpdate();
        closeConnection();
        return 1;
    } catch (SQLException e) {
    } finally {
        closeConnection();
        return -1;
    }


}




}

