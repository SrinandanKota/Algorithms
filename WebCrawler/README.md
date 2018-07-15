We will use BFS to implement a crawler to crawl and discover the wikipedia graph.

We can the model web as a directed graph. Every web page is a vertex of the graph. We put a directed edge from a page p to a page q, if
page p contains a link to page q. We can the model web as a directed graph. Every web page is a vertex of the graph. We put a directed edge
from a page p to a page q, if page p contains a link to page q.

BFS algorithm : 

1. Input: Directed Graph G = (V;E), and root 2 V .
2. Initialize a Queue Q and a list visited.
3. Place root in Q and visited.
4. while Q is not empty Do
(a) Let v be the rst element of Q.
(b) For every edge hv; ui 2 E DO
      If u =2 visited add u to the end of Q, and add u to visited.
If you output the vertices in visited, that will be BFS traversal of the input graph. We can use BFS algorithm on web graph also. Here,
the input to the algorithm is a seed url (instead of entire web graph). View that page as the the root for the BFS traversal. 
Here is the BFS algorithm on Web graph.

1. Input: seed url
2. Initialize a Queue Q and a list visited.
3. Place seed url in Q and visited.
4. while Q is not empty Do
(a) Let currentP age be the rst element of Q.
(b) Send a request to server at currentP age and download currentP age.
(c) Extract all links from currentP age.
(d) For every link u that appears in currentP age
      If u =2 visited add u to the end of Q, and add u to visited.

This will visit all the pages that are reachable from the seed url.

A web search engine is designed to crawl the web to collect material from all web pages. While doing this, the the search engine will also
construct the web graph. The structure of this graph is critical in determining the pages that are relevant to a query.This part contains a
program to focus on crawling the web and constructing a web graph. The program will crawl 200 web pages and only wiki pages

WikiCrawler

This class will have methods that can be used to crawl Wiki. This class will have following constructor and methods.
WikiCrawler.  parameters to the constructor are

1. A string seedUrl{relative address of the seed url (within Wiki domain).
2. An integer max representing Maximum number pages to be crawled.
3. An array list of keywords called topics. The keywords describe a particular topic.
4. A string fileName representing name of a le{The graph will be written to this file

extractLinks(String doc). This method gets a string (that represents contents of a .html file) as parameter. This method should return
an array list (of Strings) consisting of links from doc. Type of this method is ArrayList<String>. We assume that the html page is 
the source (html) code of a wiki page. This method will

1. Extract only wiki links. I.e. only links that are of form /wiki/XXXXX.
Only extract links that appear after the first occurrence of the html tag <p> (or <P>).
2. not extract any wiki link that contain the characters \#" or \:".
3. The order in which the links in the returned array list will be exactly the same order in which they appear in doc.

For example, if doc looks like the attached file (sample.txt), then the returned list is-

/wiki/Science, /wiki/Computation, /wiki/Procedure_(computer_science),
/wiki/Algorithm, /wiki/Information, /wiki/CiteSeerX, /wiki/Charles_Babbage

crawl() This method constructs the web graph over following pages: If seedUrl does not contain all words from topics, then the graph
constructed is empty graph(0 vertices and 0 edges). Suppose that seedUrl contains all words from topics. Consider the first max many
pages (including seedUrl page), that contain every word from the list topics, that are visited when you do a BFS with seedUrl as root.
The program constructs the web graph only over those pages. and writes the graph to the file fileName.

For example, WikiCrawler can be used in a program as follows. Say topics has strings Iowa State, Cyclones.

WikiCrawler w = new WikiCrawler("/wiki/Iowa_State_University", 100, topics, "WikiISU.txt");
w.crawl();

This program will start crawling with /wiki/Iowa State University as the root page. It will collect the first 100 pages that contain
both the words \Iowa State" and "Cyclones" that are visited by a BFS algorithm. Determines the graph (links among the those 100 pages)
and writes the graph to a (text) file named WikiISU.txt. This file will contain all edges of the graph. Each line of this file should
have one directed edge, except the first line. The first line of the graph should indicate number of vertices which will be 100. Below 
is sample contents of the file

100

/wiki/Iowa_State_University /wiki/Flagship

/wiki/Iowa_State_University /wiki/Land-grant_university

/wiki/Iowa_State_University /wiki/Space_grant_colleges

/wiki/Iowa_State_University /wiki/Ames,_Iowa

/wiki/Iowa_State_University /wiki/Iowa

/wiki/Iowa_State_University /wiki/Carnegie_Classification_of_Institutions_of_Higher_Education

/wiki/Iowa_State_University /wiki/Carnegie_Foundation_for_the_Advancement_of_Teaching

/wiki/Iowa_State_University /wiki/Association_of_American_Universities

/wiki/Iowa_State_University /wiki/Coeducational

/wiki/Iowa_State_University /wiki/Iowa_Legislature

/wiki/Iowa_State_University /wiki/Morrill_Land-Grant_Colleges_Act

.....

.....

.....

3


The first line tells that there is a link from page /wiki/Iowa State University to the page /wiki/Flagship. All other methods of the
class must be private.

