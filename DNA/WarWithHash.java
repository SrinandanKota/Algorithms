import java.util.*;


public class WarWithHash
{
	HashSet<String> sa=new HashSet<String>();// member fields and methods
	Hashtable<Integer, String> hash_k= new Hashtable<Integer, String>();
	String[] list_k;
	ArrayList<String> list_2k=new ArrayList<String>();//storing k-length strings
	int kd=0;//store k value
	
	public WarWithHash(String[] s, int k)
	{
		this.list_k=s;
		this.kd=k;
/*		for (int i=0;i<s.length;i++)
		{
			sa.add(s[i]);
		}*/
		
		//storing k-length strings in hashtable
		for(int i=0;i<s.length;i++)
		{
			hash_k.put(this.hash_begn(s[i]), s[i]);
		}
		// implementation
				
	}
	
	public ArrayList<String> compute2k()
	{

		String sc="";//to hold string to be checked
		int hashval=0;
		// implementation
		
		//construct 2k-length substrings
		for (Map.Entry n:hash_k.entrySet()){
			for (Map.Entry p:hash_k.entrySet()){
				list_2k.add(n.getValue().toString()+p.getValue().toString());
				//sg.put(this.hash_begn(n+p),n+p);
				//System.out.println(list_2k);
			}
		}
		
		
		Iterator<String> itr = list_2k.iterator();
		while (itr.hasNext()) {
			sc = (String)itr.next();
			//System.out.println(sc);
			for (int j=0;j<=sc.length()-this.kd;j=j+1)
			{
				//System.out.println(sc.substring(j,j+this.kd));
			hashval=this.hash_begn(sc.substring(j,j+this.kd));	
			if(!hash_k.containsKey(hashval))
			{
				itr.remove();
			}
			}
			
		}
		
		//System.out.println(sb);
		return list_2k;
	}
	
	//calculate hash values of strings
	public int hash_begn(String s_enc)
	{
		char[] ch=s_enc.toCharArray();
		double first_encodeval=0;
		int l_p=104395349;//large prime
		int LoadFactor=26;
		for (int i=0;i<ch.length;i++)
		{
			double da=java.lang.Math.pow(LoadFactor,ch.length-i-1);
			//System.out.println(da);
			first_encodeval=first_encodeval+da*(int)ch[i];
			//System.out.println(first_encodeval);
		}
		//System.out.println(first_encodeval);
		return (int)first_encodeval%l_p;
	}
	
	public static void main(String[] args){
		String u="ACCACC";
		String[] s=new String[3];
		ArrayList<String> list_tp=new ArrayList<String>();
		int j=0;
		int k=2;
		
		for (int i=0;i<=u.length()-k;i=i+k)
		{
			s[j++]=u.substring(i, i+k);
		}
		
		//for (int m=0;m<s.length;m=m+1)
		//{
		//	System.out.println(s[m]);
		//}
		
		WarWithHash wh=new WarWithHash(s,k);
		list_tp=wh.compute2k();
		System.out.println(list_tp);
		
	}
}
