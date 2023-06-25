package com.example.mafhoumfz;

import java.io.*;

public class writeread {
    File fichier;

    public writeread(File fichier) {
        this.fichier = fichier;
    }
    void write(Manager m) throws IOException {
        Manager nj;
        ObjectInputStream entree =  null;
        ObjectOutputStream sortie = null;

        boolean flag = false;
        File temporaire = new File("temp.txt");
        sortie = new ObjectOutputStream(new FileOutputStream(temporaire));
        try {
            entree = new ObjectInputStream(new FileInputStream(fichier));
            nj = (Manager) entree.readObject();
            while (nj !=null){
                if (m.id.equals(nj.id)){
                    sortie.writeObject(m);
                    flag = true;
                }
                else {
                    sortie.writeObject(nj);
                }
            }
        }catch (FileNotFoundException e){

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (!flag)
            sortie.writeObject(m);
        sortie.close();
        fichier.delete();
        temporaire.renameTo(fichier);
    }
}
