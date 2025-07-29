package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
@Command(
        name="gendiff",
        mixinStandardHelpOptions = true,
        description="Compares two configuration files and shows a difference.")

public class App implements Runnable{


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Запущено приложение");
        System.out.println("Verbose: " );
    }
}
