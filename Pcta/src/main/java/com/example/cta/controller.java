package com.example.cta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;


public class controller {
    String Sname = "";
    String Susn = "";
    int rmNo = 0;
    int dskNo = 0;
    String exType="";
    String branch="";
    int i=0,j=0;
    @FXML
    private TextField name;
    @FXML
    private TextField usn;
    @FXML
    private TextField roomno;
    @FXML
    private TextField deskno;
    @FXML
    private ComboBox<String> cbBranch,cbExamType;
    @FXML
    private TableView<student> tbl;
    @FXML
    private TableColumn<student,String> nameCol,usnCol,branchCol,examTypeCol;
    @FXML
    private TableColumn<student,Integer> deskNoCol,roomNoCol;
    @FXML
    private Label resStu,resBranch;

    String URL = "jdbc:mysql://localhost/examination";

    public void loadData(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        usnCol.setCellValueFactory(new PropertyValueFactory<>("usn"));
        branchCol.setCellValueFactory(new PropertyValueFactory<>("branch"));
        examTypeCol.setCellValueFactory(new PropertyValueFactory<>("examType"));
        deskNoCol.setCellValueFactory(new PropertyValueFactory<>("deskNo"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
    }

    ObservableList<String> branchVal = FXCollections.observableArrayList("Computer science and engineering","Information science and engineering","Mechanical Engineering","Civil Engineering","Electronics And Communication Engineering");
    ObservableList<String> examType = FXCollections.observableArrayList("Theory","Practical");
    public void initialize(){
        cbBranch.setItems(branchVal);
        cbExamType.setItems(examType);
    }
    @FXML
    public void onSub() {
        String query1 = "select count(usn) from student";
        String query2 = "select count(distinct branch) from student";
        int stuCount=0,branchCount=0;
        try {
            Connection connection = DriverManager.getConnection(URL,"root","groot");
            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery(query1);
            resultSet1.next();
            stuCount = resultSet1.getInt(1);
            ResultSet resultSet2 = statement.executeQuery(query2);
            resultSet2.next();
            branchCount = resultSet2.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        resStu.setText(String.valueOf(stuCount));
        resBranch.setText(String.valueOf(branchCount));
    }
    public void onAdd() throws Exception {
        Sname = name.getText();
        Susn = usn.getText();
        rmNo = Integer.parseInt(roomno.getText());
        dskNo = Integer.parseInt(deskno.getText());
        branch = cbBranch.getValue();
        exType = cbExamType.getValue();
        String query = "insert into student"+"(name,usn,branch,examType,roomNo,deskNo)"+"values(?,?,?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(URL,"root","groot");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,Sname);
            preparedStatement.setString(2,Susn);
            preparedStatement.setString(3,branch);
            preparedStatement.setString(4,exType);
            preparedStatement.setInt(5,rmNo);
            preparedStatement.setInt(6,dskNo);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        ObservableList<student> data = FXCollections.observableArrayList(new student(Sname,Susn,rmNo,dskNo,branch,exType));
        loadData();
        tbl.setItems(data);
    }

    @FXML
    public void onDisplyBtnClk(){
        ObservableList<student> data = FXCollections.observableArrayList();
        String query = "select * from student";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "groot");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data.add(new student(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(3),resultSet.getString(4)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        loadData();
        tbl.setItems(data);

    }

    public void onUpdateBtnClk(){
        rmNo = Integer.parseInt(roomno.getText());
        dskNo = Integer.parseInt(deskno.getText());
        Susn = usn.getText();
        String query = "update student set roomNo="+rmNo+",deskNo="+dskNo+" where usn='"+Susn+"'";
        try{
            Connection connection = DriverManager.getConnection(URL,"root","groot");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void onDeleteBtnClk(){
        Susn = usn.getText();
        String query = "delete from student where usn='"+Susn+"'";
        try{
            Connection connection = DriverManager.getConnection(URL,"root","groot");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}


