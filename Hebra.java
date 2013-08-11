import java.util.Random;

  public class Hebra extends Thread {

	  ReadersWriters2 rw;
	  
    public Hebra(ReadersWriters2 rw) { // constructor

      this.rw=rw;

    }

    public void run (){
    	
    	try{
    		
    		while(true) {

    			rw.startWrite();
    			rw.stopWrite();

    		}
      
    	}
    	
    	catch(InterruptedException ie)
    	{
    	}

    }

  }
