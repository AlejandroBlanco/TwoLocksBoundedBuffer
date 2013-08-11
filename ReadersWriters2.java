import java.util.Random;


  public class ReadersWriters2 {

	public void startWrite() throws InterruptedException {

      synchronized(OkToWrite) { // condition variable lock

        synchronized(this) { // monitor lock

          if(writing || waitingReaders > 0 || isFull()) {

            waitingWriters++;

            OkToWrite.wantToSleep = true;

          } if(isEmpty()){

            writing = true;

            OkToWrite.wantToSleep = false;

            OkToWrite.wakeUp = true;

          } 

        } // Give up monitor lock.

        if(OkToWrite.wantToSleep) OkToWrite.wait();
        
        while (!OkToWrite.wakeUp || isFull()) {
        	
        	OkToWrite.wait();
        }

        write(new Random().nextInt(1000));

      } // Give up OkToWrite lock.

    }



    public void stopWrite() {

      synchronized(OkToRead) { // get locks in correct order

        synchronized(OkToWrite) {

          synchronized(this) {

        	  if(isEmpty()) {
            	  
        		  writing = false;
            	  
        		  OkToWrite.wantToSleep = false;
              
        		  OkToWrite.wakeUp = true;

        		  OkToWrite.notifyAll();

        	  } else if(( waitingReaders > 0 && !isEmpty() ) || isFull()) {

        		  writing = false;

        		  OkToRead.wantToSleep = false;
            	
        		  OkToRead.wakeUp = true;

        		  OkToRead.notifyAll();

            } else writing = false;
        	  
        	  if(waitingWriters > 0) {

              waitingWriters--;

            }
              

          } // Give up monitor lock.

        } // Give up OkToWrite lock.

      } // Give up OkToRead lock.

    }



    public void startRead() throws InterruptedException {

      synchronized(OkToRead){

        synchronized(this) {

          if(writing || isEmpty()) {

            waitingReaders++;

            OkToRead.wantToSleep = true;
            
            OkToRead.wakeUp = false;
            
            OkToWrite.wakeUp = true;

          } else {

            OkToRead.wantToSleep = false;

            OkToRead.wakeUp = true;

          }

        } 

        if(OkToRead.wantToSleep) OkToRead.wait();

        while(!OkToRead.wakeUp || isEmpty()) OkToRead.wait();

        read();
      } 

    }



    public void stopRead() {

      synchronized(OkToWrite) {

        synchronized(this) {

        	if(waitingReaders > 0)
        	
        	waitingReaders--;

        	if(isEmpty()){

            writing = false;
            
            OkToRead.wantToSleep = true;

            OkToRead.wakeUp = false;
            
            OkToWrite.wantToSleep = false;

            OkToWrite.wakeUp = true;

            OkToWrite.notifyAll();
        	  
          }

        }

      }

    }
    
    
    synchronized public void write(int value){
    	bb.addValue(value);
    }
    
    synchronized public int read(){
    	return bb.getValue();
    }

    public boolean isFull(){
  	  return bb.getPosition()==20;
    }
    
    public boolean isEmpty(){
  	  return bb.getPosition()==0;
    }

    private int readers = 0;

    private int waitingReaders = 0;

    private int waitingWriters = 0;

    private boolean writing = false;

    private ConditionVariable OkToRead =

                new ConditionVariable();

    private ConditionVariable OkToWrite =

                new ConditionVariable();
    
    private BoundedBuffer bb = new BoundedBuffer(20);

  }
