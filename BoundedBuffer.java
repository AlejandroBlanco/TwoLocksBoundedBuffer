  public class BoundedBuffer {

	  static int[] buffer;
	  
	  static int last = 0;

	  static int first = 0;
      
      static int size;
	  
      public BoundedBuffer(int length) {

      size = length;

      buffer = new int[length] ;

    }
      
      public static void decrementLast(){
    	  last=last-1;
      }
      
      public static void augmentLast(){
    	  last=last+1;
      }
    
      public static int getValue(){
    	  decrementLast();
    	  System.out.println("Pos at getValue: "+(last));
    	  return buffer[last];
      }
      
      public static void addValue(int data){
    	  buffer[last]=data;
          augmentLast();
    	  System.out.println("Pos at addValue: "+(last));
      }
      
      public static int getPosition(){
    	  return last;
      }
      
      public static boolean isFull(){
    	  System.out.println("Is Full: "+(last==size));
    	  return last==size;
      }
      
      public static boolean isEmpty(){
    	  System.out.println("Is Empty: "+(last==-1));
    	  return last==-1;
      }

  }
