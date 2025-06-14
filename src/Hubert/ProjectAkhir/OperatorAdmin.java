package semester2.src.Hubert.ProjectAkhir;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class OperatorAdmin {
    List<Admin> adminList;
    File adminFile = new File("dataAdmin.txt");
    public OperatorAdmin() {
        adminList = new ArrayList<>();
    }
    public boolean cekAdmin(String email, String nama, String password) throws Exception {
        try{
            FileReader fr = new FileReader(adminFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(";");
                if(parts[0].equals(email) && parts[1].equals(nama) && parts[2].equals(password)){
                    return true;
                }
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return false;
    }
    public void daftarAdmin(String email, String nama, String password) throws Exception {
        try{
            if(cekAdmin(email, nama, password)){
                throw new Exception("Admin sudah terdaftar!");
            }else {
                FileWriter fw = new FileWriter(adminFile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(email + ";" + nama + ";" + password);
                bw.newLine();
                bw.close();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
