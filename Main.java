
public class Main {

	public static void main(String[] args) {

		try{
		ReadersWriters2 rw=new ReadersWriters2();
		Hebra h1=new Hebra(rw);
		Hebra h2=new Hebra(rw);
		Hebra h3=new Hebra(rw);
		HebraRead h4=new HebraRead(rw);
		HebraRead h5=new HebraRead(rw);
		HebraRead h6=new HebraRead(rw);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		}catch(Exception e){}
	}

}
