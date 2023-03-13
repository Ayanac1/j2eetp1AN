import dao.Idao;
import dao.daoVolatile.releveDao;
import metier.IreleveMetier;
import metier.releveMetier;
import modele.releve;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import presentation.IreleveControleur;
import presentation.releveControleur;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;


public class CalculDeReleveDeNote_App {

    static releveControleur releveControleur;
    static Scanner clavier = new Scanner(System.in);

    private static boolean estUnNombre(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static void test1() {
        //instanciation des différents composants de l'application
        releveDao dao = new releveDao();
        var metier = new releveMetier();
        var controleur = new releveControleur();

        //injection des dépendances
        metier.setReleveDao(dao);
        controleur.setReleveMetier(metier);

        //tester

        String rep = "";
        do {
            System.out.print("=>[Test1] Calcule de Mensualité de releve <=\n");
            try {
                String inpuut = "";
                while (true) {
                    System.out.print("=>Entrez l'id du releve:");
                    inpuut = clavier.nextLine();
                    if (estUnNombre(inpuut)) break;
                    System.err.println("Entrée non valide!!!");
                }
                long id = Long.parseLong(inpuut);
                controleur.afficher_Mensualite(id);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.print("Voulez vous quittez(oui/non)?");
            rep = clavier.nextLine();
        } while (!rep.equalsIgnoreCase("oui"));
        System.out.println("Au revoir ^_^");
    }
    public static void test2() throws Exception{
        String daoClass;
        String serviceClass;
        String controllerClass;
        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");


        if (propertiesFile == null) throw new Exception("fichier config introuvable !!!");
        else {
            try {
                properties.load(propertiesFile);
                daoClass = properties.getProperty("DAO");
                serviceClass = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            } catch (IOException e) {
                throw new Exception("Probleme de chargement des propriétés du fichier config");
            } finally {
                properties.clear();
            }
        }
        try {
            Class cDao = Class.forName(daoClass);
            Class cMetier = Class.forName(serviceClass);
            Class cControleur = Class.forName(controllerClass);

            Idao<releve, Long> dao = (Idao<releve, Long>) cDao.getDeclaredConstructor(Idao.class).newInstance(cDao);
            IreleveMetier metier = (IreleveMetier) cMetier.getDeclaredConstructor().newInstance();
            releveControleur = (presentation.releveControleur) cControleur.getDeclaredConstructor().newInstance();

            Method setDao = cMetier.getMethod("setCreditDao", releveDao.class);
            setDao.invoke(metier, dao);
            Method setMetier = cControleur.getMethod("setCreditMetier", releveMetier.class);
            setMetier.invoke(releveControleur, metier);
            releveControleur.afficher_Mensualite(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test3()throws Exception{
        ApplicationContext context= new ClassPathXmlApplicationContext("spring-ioc.xml");
        releveControleur = (presentation.releveControleur) context.getBean("controleur");
        releveControleur.afficher_Mensualite(1L);
    }

    public static void test4()throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext("dao","Metier","presentation");
        releveControleur = (presentation.releveControleur) context.getBean(IreleveControleur.class);
        releveControleur.afficher_Mensualite(1L);
    }

    public static void main(String[] args) throws Exception {
        test2();
    }
}
