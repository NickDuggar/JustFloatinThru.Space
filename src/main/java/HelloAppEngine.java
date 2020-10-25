import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
// be legit pls
	String parrotLink = "";
	// let's make a horoscope
	switch((int) (Math.random()*6)) {
	    case 0:
	    	// general parrot happiness
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/parrot.gif";
	    	break; //takin a break from homework
	    case 1: 
	    	// parrot sadness
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/sadparrot.gif";
	        break; // your heart
	    case 2:
	    	// anger
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/angryparrot.gif";
	    	break; // the nearest window
	    case 3:
	    	//hmm
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/hmmparrot.gif";
	    	break; // your expectations
	    case 4:
	    	// party
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/partyparrot.gif";
	    	break;
	    case 5:
	    	// bday but no one remembered
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/birthdaypartyparrot.gif";
	    	break; // my heart
	    case 6:
	    	// boredom
	    	parrotLink = "https://cultofthepartyparrot.com/parrots/hd/boredparrot.gif";
	    	break;
		default:
			// be sad
			parrotLink = "https://cultofthepartyparrot.com/parrots/hd/parrotnotfound.gif";
	}
	 
	ServletContext sc = getServletContext(); // there's no way in hell that this is going to work
	 try (InputStream is = sc.getResourceAsStream(parrotLink)) {

         // it is the responsibility of the container to close output stream
         OutputStream os = response.getOutputStream();

         if (is == null) {

             response.setContentType("text/plain");
             os.write("Failed to send image. Your horoscope is uncertain".getBytes());
         } else {

             response.setContentType("image/jpeg");

             byte[] buffer = new byte[1024];
             int bytesRead;

             while ((bytesRead = is.read(buffer)) != -1) {

                 os.write(buffer, 0, bytesRead);
             }
         }
     }
	  
	// this is all that servlet printing stuff 
//    response.setContentType("text/plain");
//    response.setCharacterEncoding("UTF-8");
//
//    response.getWriter().print("Hello App Engine!\r\n");
//    response.getWriter().print("<img src=\"https://cultofthepartyparrot.com/parrots/hd/imposterparrot.gif\">");
  }
}