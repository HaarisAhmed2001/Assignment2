import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
    protected Socket socket = null;
    protected PrintWriter out = null;
    protected BufferedReader in = null;

    // create a constructor for this Server thread
    public ServerThread(Socket socket) {
        // call super
        super();

        // set the socket
        this.socket = socket;

        // use try catchto control the information that goes within the server and out
        // of the server
        try {
            // create an in variable which will send the data into the proram
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // create a variable that will take data out from the program
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            // output error
            System.err.println("IOEXception while opening a read/write connection");
        }
    }

    // create the run function
    public void run() {
        // initialize interaction
        out.println("Connected to the Server");

        // create a boolean variable to keep track of the process
        boolean endOfSession = false;

        // loop until the proccess is finished
        while (!endOfSession) {
            endOfSession = processCommand();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create the process command function
    protected boolean processCommand() {
        // create a string variable that will be a holder for commands
        String holder = null;

        try {
            // set holder to holding the info
            holder = in.readLine();
        } catch (IOException e) {
            // output error message
            System.err.println("Error reading command from socket.");
            return true;
        }
        // use an if statement to check if the holder is null
        if (holder == null) {
            // return true
            return true;
        }

        // create a tokenizer variable
        StringTokenizer st = new StringTokenizer(holder);

        // create a string variable that will hold the command
        String commands = st.nextToken();

        // create the variables that will be proccessed to execute the commands
        String info = null;
        String names = null;

        if (st.hasMoreTokens()) {
            names = holder.substring(commands.length() + 1, holder.length());
        }

        // return the outcome of the process
        return processCommand(commands, info, names);
    }

    // set up the actual process
    protected boolean processCommand(String commands, String info, String names) {
        // use an if statement to determine which command shall be exectuted
        if (commands.equalsIgnoreCase("UPLOAD")) {
            // ouput thhat the command has commenced
            System.out.println("Uploading has commenced");

            // use try catch to check for any errors
            try {
                // set a create a file variable that will be used to hold the file's content
                File contentUpload = new File("/shared/" + names);

                // create a writer variable to write down all of the information
                FileWriter writer = new FileWriter(contentUpload);

                // open up the file writer to upload the content from content uplod
                writer.write(info);
                // close the writer
                writer.close();
            } catch (Exception e) {
                // output error
                System.out.println("Upload failed");
            }

            // return true to the user
            return true;

        } else if (commands.equalsIgnoreCase("DOWNLOAD")) {
            // output the command has commenced
            System.out.println("Downloading has commenced");

            // create a string file that will take in the file's information
            String absorb = null;

            // use trycatch to catch any error
            try {
                // create a file variable that represents the File in question
                File curFile = new File("/shared" + names);

                // use an if statement to check if the file exists
                if (!curFile.exists()) {
                    // output the error
                    System.out.println("The file does not exist");
                } else {

                    // create a scanner variable to store the information into the absorb variable
                    Scanner noter = new Scanner(curFile);

                    // use a while loop to loop through the file's content and store them into the
                    // absorb variable
                    while (noter.hasNextLine()) {
                        // add the information to absorb
                        absorb += noter.nextLine();
                    }

                    // output success
                    System.out.println("Download has been completed");
                }
            } catch (Exception e) {
                // output error
                System.out.println("Download Failed");
            }
            // return true to the user
            return true;
        } else if (commands.equalsIgnoreCase("DIR")) {
            // return true to the user
            // ouput the command has been set
            System.out.println("The Directories have been set");

            // create a variable that will be the representation of folder and files being
            // shared
            // among their peers
            File distributedFiles = new File("/share");

            // create a File array based on the distributedFiles variable
            File[] fileList = distributedFiles.listFiles();

            // create a for loop that will display the name of each file to the user, using
            // a dummy file variable
            for (File fileHolder : fileList) {
                if (!fileHolder.isFile()) {
                    // do nothing
                } else {
                    // output the file to the user
                    System.out.println(fileHolder.getName());
                    System.out.println();
                }
            }
            return true;
        } else {
            // output invalid command
            System.out.println("command is nonexistant");
            // return true to the user
            return true;
        }
    }
}
