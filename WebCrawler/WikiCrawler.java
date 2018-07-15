import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Map;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.*;
//import java.net.MalformedURLException;
//import java.net.URL;



public class WikiCrawler
{
	static final String BASE_URL = "https://en.wikipedia.org";//base url
	static int url_req=0;//request tracker
	Hashtable<Integer, String> hash_k= new Hashtable<Integer, String>();//keep unique links
	String f_write="";//file to write
	String root_lnk="";//seed
	ArrayList<String> list_topics = new ArrayList<String>();//topic
	int max_node;
	// other member fields and methods

	public WikiCrawler(String seedUrl, int max, ArrayList<String> topics, String fileName)
	{
		this.max_node=max;
		this.list_topics=topics;
		this.f_write=fileName;
		this.root_lnk=seedUrl;
		// implementation
		//this.GetSimpleLinks(seedUrl);
		//this.bfs_graph(seedUrl);
	}

	
	private ArrayList<String> GetSimpleLinks(String seedUrl)
	{
		//get links without images or links to other sections
		ArrayList<String> ext_link = new ArrayList<String>();
		String doc_ext="";
		try{
			URL bas_url= new URL(BASE_URL);
			URL rel_url = new URL(bas_url, seedUrl);
			URLConnection conn = rel_url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder response= new StringBuilder();
			String inputLine="";
			while ((inputLine = br.readLine())!=null)
			{
				response.append(inputLine);					
			}
			br.close();
//			System.out.println(response.toString());
			doc_ext=response.toString();// get url in string
		} 
	catch (MalformedURLException e) {
			e.printStackTrace();
		} 
	catch (IOException e) {
			e.printStackTrace();
		}
//	System.out.println(doc_ext);
	ext_link=this.extractLinks(doc_ext);
//	System.out.println(ext_link);
	
		//keep track of the number of requests
		url_req++;
		if(url_req==50)
		{
			url_req=0;
			try{Thread.sleep(3000);}catch(InterruptedException e){System.out.println(e);} 
		}
		
		int topic_cnt=0;		//check topic content
		Iterator<String> itr = list_topics.iterator();
		while (itr.hasNext()) {
			String checkstr=(String)itr.next();
			if (doc_ext.contains(checkstr))
			{
				topic_cnt=topic_cnt+1;
			}
			
		}	
		
	
//		check for topics in page
		if(topic_cnt==list_topics.size())
		{
//			System.out.println(ext_link);
			return ext_link;
		}
		else
		{
			return null;
		}			

	
	
	}
	
	
	// NOTE: extractLinks takes the source HTML code, NOT a URL
	public ArrayList<String> extractLinks(String doc)
	{
		ArrayList<String> grp = new ArrayList<String>();//holds links in a page 
		// implementation
		int index_P=doc.indexOf("<P>");
		int index_p=doc.indexOf("<p>");
		if (index_P==-1)
		{
			index_P=index_p+1;
		}
		else
		{
			index_p=index_P+1;
		}
		int first_p=(index_P<index_p?index_P:index_p);//getting index of <p> or <P>
		doc=doc.substring(first_p);//getting links after <p> or <P>
		int firstLinkStart=doc.indexOf("href=\"/");//link of /wiki/xxxx
		int nextLinkStart=firstLinkStart;//link ending 
		int nextLinkDone=doc.indexOf("\"",firstLinkStart+6);//next link
		while(nextLinkStart!=-1&&nextLinkDone!=-1)
		{
			
			String holdLink=doc.substring(nextLinkStart+6,nextLinkDone);//hold link
			nextLinkStart=doc.indexOf("href=\"/",nextLinkStart+6);
			nextLinkDone=doc.indexOf("\"",nextLinkStart+6);
			if(holdLink.contains("#")||holdLink.contains(":")||holdLink.contains("/w/")||holdLink.contains("//"))
			{
				continue;
			}
			grp.add(holdLink);

			
		}
//		System.out.println(grp);
		return grp;
	}

	
	private ArrayList<String> bfs_graph(String v_i)
	{
		ArrayList<String> bfs_links = new ArrayList<String>();//to hold bfs vertices
		ArrayList<String> bfs_links_in = new ArrayList<String>(); 
		Queue<String> Q = new LinkedList<>();//queue
		ArrayList<String> visited = new ArrayList<String>();//vertices of the graph
		ArrayList<String> edge = new ArrayList<String>(); // edges of the graph
		int flag_a=0;
		int pg_cnt=0;
		
		//add first vertex
		Q.add(v_i);
		visited.add(v_i);
//		edge.add(v_i);
		pg_cnt++;
		
		
		while (Q.peek() != null) 
		{
			if(pg_cnt>max_node-1)
				break;
			
			String currentPage=Q.remove();
			bfs_links=this.GetUniqueLinks(currentPage);

			if(bfs_links!=null)
			{
				Iterator<String> itr = bfs_links.iterator();
				while(itr.hasNext()&&(pg_cnt<=max_node-1))
				{
					
					String link_v = (String)itr.next();
					bfs_links_in=this.GetUniqueLinks(link_v);
					if(bfs_links_in!=null&&!visited.contains(link_v))
					{
						Q.add(link_v);
						pg_cnt++;
						//add other vertices till we reach max
						visited.add(link_v);						
					}	
					

				}

			}
		}
		
		
//  	Creating graph  and write to file
		PrintWriter pw = null;
		System.out.println("greaph");
		System.out.println(visited.size());
		try
		{
//		int edg_cnt=0;
		pw = new PrintWriter( new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(this.f_write)),"UTF-8"));
		pw.println(visited.size());
		Iterator<String> itr_b = visited.iterator();
		while (itr_b.hasNext()) {
			String link_v = (String)itr_b.next();
			Iterator<String> itr_c = this.GetUniqueLinks(link_v).iterator();
			while (itr_c.hasNext())
			{
				String link_w = (String)itr_c.next();
//				edge.add(link_w);
				if(visited.contains(link_w)&&(!link_w.equals(link_v)))
				{
				System.out.println(link_v+" "+link_w);
				String edge_w=link_v+" "+link_w;
				pw.println(edge_w);
//				edge.add(edge_w);
				}
			}	
		}
		
		pw.close();
		
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally{
			if(pw!=null){
				pw.flush();
				pw.close();
			}
		}
	
		
//		System.out.println(edge.size());
		
//		return visited;
		return edge;
	}
	


	private ArrayList<String> GetUniqueLinks(String page_links){
		//get unique links without multiple repeated links
		ArrayList<String> bfsUniqueLinks = new ArrayList<String>();
		ArrayList<String> bfs_links = new ArrayList<String>();
		bfs_links=this.GetSimpleLinks(page_links);	
		//consider only links to other nodes and no multiple edges
		if(bfs_links!= null)
		{
			Iterator<String> itr = bfs_links.iterator();
			int key=0;
			while (itr.hasNext()) 
			{
				String sc = (String)itr.next();
				if(!this.hash_k.contains(sc))
				{
					hash_k.put(key, sc);
					//System.out.println(hash_k.get(key));
					bfsUniqueLinks.add(hash_k.get(key));
					key=key+1;
				
				}
			}	
		}	
		
		hash_k.clear();
		return bfsUniqueLinks;
	}
	
	public void crawl()
	{
		// implementation
		ArrayList<String> final_grh = new ArrayList<String>();
		//root_lnk is seed_url
		final_grh=bfs_graph(this.root_lnk);//bfs graph of edges containing the words	
	}
	
	public static void main(String [] args)
	{
		ArrayList<String> topic = new ArrayList<String>();
		//topic.add("");
		//topic.add("Iowa State");
		//topic.add("Cyclones");
		WikiCrawler w=new WikiCrawler("/wiki/Computer_Science",200,topic,"WikiCS.txt");
		w.crawl();
	}
}
