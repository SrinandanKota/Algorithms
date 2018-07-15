import java.util.ArrayList;


public class WarWithArray
{
	ArrayList<String> list_2k=new ArrayList<String>();
	String[] list_k;//text reference
	int k=0;
	// member fields and methods
	
	public WarWithArray(String[] s, int k)
	{
		this.list_k=s;
		this.k=k;
		// implementation
	}
	
	public ArrayList<String> compute2k()
	{
		for (int i=0;i<list_k.length;i++)
		{
			for (int j=0;j<list_k.length;j++)
			{
				list_2k.add(list_k[i]+list_k[j]);
		// implementation
			}
		}
		
		//System.out.println(list_2k);
		
		for (int i=0;i<list_2k.size();i++)
		{
			
				//System.out.println(s_a.get(i+j));
				String cs=list_2k.get(i);
				//System.out.println(cs);
				for (int l=0;l<=cs.length()-this.k;l=l+1)
				{	
					//System.out.println(cs.substring(l, l+this.k));		
					if(findString(cs.substring(l, l+this.k)))
							{
								list_2k.remove(cs);
							}
							
				}
		}
		//System.out.println(list_2k);
		//prints the final strings which are in the array
		return list_2k;//returns the array
	}
	
	private boolean findString(String s_cpy) {
		boolean flag = true;
		for(int i =0;i<list_k.length;i++){
			if(s_cpy.equals((String)list_k[i])){
				//System.out.println(list_k[i]);
				//System.out.println(s_cpy);
				flag = false;
			}
		}
		return flag;
	}

	public static void main(String[] args){
	String u="AACACC";
	ArrayList<String> list_tp=new ArrayList<String>();
	//String[] st=new String[2000];
	int k=2;
	String[] st={"AC","CA","CC"};	
	//int i=0;
	//int j=0;
	//for(i=0;i<u.length()-k;i=i+k)
	//{
	//st[j++]=u.substring(i,i+k);	
	//}
	WarWithArray wa=new WarWithArray(st,k);
	list_tp=wa.compute2k();
	System.out.println(list_tp);
	}
}
