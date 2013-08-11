  public class HebraRead extends Thread {

	  ReadersWriters2 rw;
	  
    public HebraRead(ReadersWriters2 rw) { // constructor

      this.rw=rw;

    }

    public void run (){
    	
    	try{
    		
    		while(true) {

    			rw.startRead();
    			rw.stopRead();

    		}
      
    	}
    	
    	catch(InterruptedException ie)
    	{
    	}

    }

  }