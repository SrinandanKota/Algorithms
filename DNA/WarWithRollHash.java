import java.util.*;
import java.lang.Math;


public class WarWithRollHash
{	
	Hashtable<Integer, String> hash_k= new Hashtable<Integer, String>();// member fields and methods
	String[] list_k;
	ArrayList<String> list_2k=new ArrayList<String>();
	int kd=0;//k value copied into this for internal computation
	int l_p=104395387;
	//large prime
	int LoadFactor=26;
	int first_h=0;//value of first hash string
	
	public void encode_h(String s_encode)
	{
		char[] ch=s_encode.toCharArray();
		int first_encodeval=0;
		//int LoadFactor=26;
		//long s_encodeval=0;
		for (int i=0;i<ch.length;i++)
		{
			first_encodeval=first_encodeval+(int)java.lang.Math.pow(this.LoadFactor,ch.length-i-1)*(int)ch[i];
		}
			//System.out.println(first_encodeval);
		this.first_h=first_encodeval%this.l_p;//store the first hash value
		//System.out.println(this.first_h);
	}
	
	//calculate the first hash value
	public int hash_begn(String s_enc)
	{
		char[] ch=s_enc.toCharArray();
		double first_encodeval=0;
		//int LoadFactor=26;
		//long s_encodeval=0;
		for (int i=0;i<ch.length;i++)
		{
			//System.out.println((int)ch[i]);
			double da=java.lang.Math.pow(this.LoadFactor,ch.length-i-1);
			//System.out.println(da);
			first_encodeval=first_encodeval+da*(int)ch[i];
			//System.out.println(first_encodeval);
		}
		//System.out.println(first_encodeval);
		return (int)first_encodeval%this.l_p;
	}
	
	//encode value
	public int encode_pre(String p_encode,String f_encode)
	{
		int db_a=(int)f_encode.charAt(0);
		int db_c=(int)java.lang.Math.pow(this.LoadFactor,f_encode.length()-1);
		int db=db_a*db_c;
		int rem_encodeval=((this.first_h-db)*(this.LoadFactor))+((int) p_encode.charAt(p_encode.length()-1));
//		System.out.println(rem_encodeval);
		return rem_encodeval;
	}//calculate the value to be subtracted from previous hash value
	
	public int hash(String s_encode,String f_encode)
	{
		int s_hash=0;
		//System.out.println(encode_pre(s_encode,f_encode)%l_p);
		return s_hash=encode_pre(s_encode,f_encode)%l_p;
	}//calculate next hash values
	
	// member fields and methods
	
	public WarWithRollHash(String[] s, int k)
	{
		//String check_n="ACCACC";
		this.list_k=s;
		this.kd=k;

		for(int l=0;l<s.length;l++)
		{
			
			hash_k.put(this.hash_begn(s[l]), s[l]);//store k-length strings to hash table

		}
		
		// implementation
	}
	
	public ArrayList<String> compute2k()
	{
		// implementation
		
		String sp="";//store string value for manipulation
		String sc="";//store string value for manipulation
		int hashval=0;
		
		
		//create 2k-length substrings		
		for (Map.Entry m: hash_k.entrySet())
		{
			for(Map.Entry n: hash_k.entrySet())
			{
				String ab=m.getValue().toString()+n.getValue().toString();
				//System.out.println(ab);
				//System.out.println(m.getValue());
				//System.out.println(n.getValue());
				list_2k.add(ab);
			}
		}
		
//		System.out.println(sb);
				
		
		Iterator<String> itr = list_2k.iterator();
		while (itr.hasNext()) {
//			System.out.println(i);
			sc = (String)itr.next();
//			System.out.println(sc);
			for (int j=0;j<=sc.length()-this.kd;j=j+1)
			{
				//calculate hash value of the first substring
				if(j==0)
				{
					sp=sc.substring(j,j+this.kd);
					hashval=hash_begn(sp);
					this.first_h=hashval;
				}//calculate hash value of other substrings using previous hash value
				else
				{
					sp=sc.substring(j-1,j-1+this.kd);
					hashval=this.hash(sc.substring(j,j+this.kd),sp);
					//hashval=hash_begn(sc.substring(j,j+this.kd));
					this.first_h=hashval;
				}
//				System.out.println(sc.substring(j,j+this.kd));
			//remove substrings which has strings not in array s
			if (hash_k.containsKey(hashval)==false) 
				{
				//System.out.println(sc.substring(j,j+this.kd));
				//System.out.println(sc);
				itr.remove();
				}
			}
		}
		
		
//		System.out.println(sb);		
		
		return list_2k;
	}
	
	public static void main(String[] args)
	{
		String u="ACCACC";
		String[] s=new String[3];
		int j=0;
		int k=2;
		ArrayList<String> list_tp=new ArrayList<String>();
		
		for (int i=0;i<=u.length()-k;i=i+k)
		{
			s[j++]=u.substring(i, i+k);
		}
		
/*		for(int i=0;i<s.length;i++)
		{
			System.out.println(s[i]);
		}*/
		
		WarWithRollHash wa=new WarWithRollHash(s,k);
		list_tp=wa.compute2k();
		System.out.println(list_tp);
	}
}
