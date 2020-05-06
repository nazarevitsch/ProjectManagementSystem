import com.bida.dbconection.service.DeveloperService;

public class Main {

    public static void main(String[] args) {
        DeveloperService developerService = new DeveloperService();
        System.out.println(developerService.findAllDevelopersByProgramingLanguage("Java"));

    }
}
