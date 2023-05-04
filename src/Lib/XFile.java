package Lib;

//import lab.Author;

import java.io.*;

public class XFile {
    public static void writeDataFile(String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(5);
            dos.writeUTF("Kim Khánh");
            dos.writeDouble(9.2);
            dos.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Failed");
        }
    }
    public static void readDataFile(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            DataInputStream dis = new DataInputStream(fis);
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readDouble());
            dis.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Failed");
        }
    }
    public static void writeBuffer(String path, String text){
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            System.err.println("Failed");
        }
    }
    public static String readBuffer(String path){
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String firstLine = br.readLine();
            while(firstLine!=null){
                sb.append(firstLine);
                firstLine = br.readLine();
                if(firstLine!=null) sb.append("\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Failed");
        }
        return sb.toString();
    }

    public static void writeObject(String path, Object obj){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(path));
            oos.writeObject(obj);
            oos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static Object readObject(String path){
        try{
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(path)
            );
            Object obj = ois.readObject();
            ois.close();
            return obj;
        }catch (IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String authorfile = "author.dat";
//        Author sp = new Author("Stephen King", "sking@somewhere.us",
//                'm',  "English",
//                new String[]{"horror","supernatural","crime",
//                        "science", "fantasy"});
//        Author conan = new Author("Conan Doyle", "cdoyle@somewhere.us", 'm',  "British",
//                new String[]{"crime", "science"});
//        List<Author> auLst = new ArrayList<>();
//        auLst.add(sp);
//        auLst.add(conan);
//        writeObject(authorfile, auLst);
//        //Read Object
//        List<Author> newAuthor =
//                (List<Author>) readObject(authorfile);
//        for (Author a: newAuthor
//             ) {
//            System.out.println(a.getName());
//        }

//        String fileText = "testBuffer.dat";
//        writeBuffer(fileText, "Trần Thị Kim Khánh \n IT 456");
//        System.out.println(readBuffer(fileText));
//        String file = "testdata.dat";
//        writeDataFile(file);
//        readDataFile(file);
    }

}
