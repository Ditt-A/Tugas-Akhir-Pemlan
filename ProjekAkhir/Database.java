package semester2.ProjekAkhir;

import java.util.*;
import java.io.*;

public class Database {
    private final static String fileUser = "Data/dataUser.txt";
    private final static String fileBuku = "Data/dataBuku.txt";

    public void addUser(Pengguna a) throws IOException {
        File file = new File(fileUser);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(a.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pengguna> readUser() throws IOException {
        List<Pengguna> Users = new ArrayList<>();
        File file = new File(fileUser);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                Pengguna user = Pengguna.fromString(line);
                if(user != null) Users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Users;
    }

    public static void deleteUser(String id) throws IOException {
        List<Pengguna> Users = readUser();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileUser))) {
            for(Pengguna user : Users) {
                if()
            }
        }
    }


}
