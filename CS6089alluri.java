
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;







public class CS6089alluri {
	
	static int n=0;
	static int h;
	static int t=1;
	static int r;
	public static void main(String[] args)throws FileNotFoundException
	{
	System.out.println("Name:vineetha Alluri");
	System.out.println("course id:CS608");
	System.out.println(java.time.LocalDate.now());
	
	
	CS6089alluri tree=new CS6089alluri();
	
	int i;	
	
	try
	{
		long d1=0,d2=0,d3=0;
	FileInputStream line=new FileInputStream("BSTData.txt");
	Scanner sc=new Scanner(line);
    d1=System.nanoTime();
	while(sc.hasNextInt() )
	{ 
		i=sc.nextInt();
		tree.insert(i);
		d2=System.nanoTime();
	    tree.avl_insert(i);
		d3=System.nanoTime();
	   }
   
long D2=d2-d1;
long D3=d3-d2;

System.out.println("Time taken to construct a binary tree is:" +D2);
System.out.println("Time taken to construct AVL tree is:" +D3);
System.out.println("Binary tree inorder:");
tree.inorder(tree.root);
System.out.println("Avl tree inorder:");
tree.avl_inorder(tree.root1);
System.out.println("Binary Level order of elements:");
tree.print_level_elements(tree.root);
System.out.println("AVL level order of elements:");
tree.avl_print_level_elements(tree.root1);
tree.height_access();
int v=tree.largest(tree.root);	
System.out.println("Largest elementin Binary tree is: "+v);
int c=tree.avl_largest(tree.root1);
System.out.println("Largest element in AVL tree is: "+c);
sc.close();
Scanner sc1=new Scanner(System.in);
while(true)
{

System.out.println("Enter the number you want to search for in the BST & avl: ");
int b=sc1.nextInt();
if(b!=0)
{
long s1=System.nanoTime();	
boolean validate= search_for(tree.root,b);
long s2=System.nanoTime();
long N3=s2-s1;
System.out.println("Time taken to validate if it is in BST:"+N3);
System.out.println("Element is found:" +validate);
long s3=System.nanoTime();
boolean validate1=avl_search_for(tree.root1,b);
long s4=System.nanoTime();
long N4=s4-s3;
System.out.println("Time taken to validate if is in AVL: "+N4);
System.out.println("Element is found: " +validate1);
continue;
}

else 
{
break;	
}

}
sc1.close();


	}
	catch(Exception e)
	{
		System.out.println("File Not Found");
	}
	
int number=numberofnodes(tree.root);
System.out.println("Number of nodes:"+number);

	}
	//Binary search tree without duplicate values 
	class Node{
		int key;
		Node left,right;
		public Node(int node_value)
		{
			key=node_value;
			left=right=null;
			}
		
	}
	class AVL_Node
	{
		AVL_Node left;
		AVL_Node right;
		int key;
		int height;
		public AVL_Node(int n)
		{
			left=null;
			right=null;
			key=n;
			height=0;
			
		}
		
		
	}
    AVL_Node root1;
	Node root;
	
	CS6089alluri()
	{
		root=null;
		root1=null;
		
		
	}
	void avl_print_level_elements(AVL_Node root)
	{
		int h=height(root1);
		int p;
		
		for(p=0;p<=h;p++)
		{   
			avl_level_elements(root1,p);
			System.out.println(" ");
		}
		System.out.println("height of avl tree is "+h);
		}
	void avl_level_elements(AVL_Node root1,int level)
	{
		
		if(root1==null)return;
		
		if(level==0)
		{
			
			System.out.println(root1.key+" ");
		}
		
		else if(level>0) 
		{
		    avl_level_elements(root1.left,level-1);
			avl_level_elements(root1.right,level-1);
		}
		
	}

	
	int maximum(int p,int q)
	{
		if(p>q)
		{
			return p;
		}
		else
		{
			return q;
		}
			}
	int height(AVL_Node node)
	{
		if(node==null)
			return 0;
		else
			return(node.height);
	}
					
	AVL_Node rightrotate(AVL_Node nrvalue)
	{
		AVL_Node temp=nrvalue.left;
		nrvalue.left=temp.right;
		temp.right=nrvalue;
		
		nrvalue.height=maximum(height(nrvalue.left),height(nrvalue.right))+1;
		temp.height=maximum(height(temp.left),height(temp.right))+1;
		
		return temp;
	}
	AVL_Node leftrotate(AVL_Node nlvalue)
	{
		AVL_Node temp1=nlvalue.right;
		nlvalue.right=temp1.left;
		temp1.left=nlvalue;
		
		nlvalue.height=maximum(height(nlvalue.left),height(nlvalue.right))+1;
		temp1.height=maximum(height(temp1.left),height(temp1.right))+1;
		
		return temp1;
	}

	AVL_Node doublerightrotate(AVL_Node x)
	{
		return rightrotate(x);
		
	}
	AVL_Node doubleleftrotate(AVL_Node x)
	{
		x.right=rightrotate(x.right);
		return leftrotate(x);
	}
	
	AVL_Node inserting(AVL_Node node,int key)
	{
		if(node==null)
		{
			node=new AVL_Node(key);
		}
		else if(key<node.key)
		{
			node.left=inserting(node.left,key);
			{
			if(height(node.left)-height(node.right)==2)
            {
            if(key<node.left.key)
            {
            	node=rightrotate(node);
            }
            else
            {
            	node=doublerightrotate(node);
            }
            	}
            }
		}
		else if(key>node.key)
		{
			node.right=inserting(node.right,key);
			if(height(node.right)-height(node.left)==2)
			{
				if(key>node.right.key)
				{
					node=leftrotate(node);
				}
				else
				{
					node=doubleleftrotate(node);
				}
			}
		}
		else
			System.out.println("duplicate element exists");
		    node.height=maximum(height(node.left),height(node.right))+1;
		    return node;
		
	}
	
void avl_insert(int key)
	{
		root1=inserting(root1,key);
		
	}

void avl_inorder(AVL_Node root1)
{
	if(root1!=null)
	{
		avl_inorder(root1.left);
		System.out.println(root1.key);
		avl_inorder(root1.right);
	}
	}

	
	

	void insert(int key)
	{
		root=insert_node(root,key);
		n++;
		
	}
	
	
	


Node insert_node(Node root,int key)
	{
		
		if(root==null)
		{
			root=new Node(key);
			return root;
		}
		else if(key<root.key)
		{
			root.left=insert_node(root.left,key);
		}
		else if(key>root.key)
		{
		root.right=insert_node(root.right,key);	
		}
		return root;
		
	}
	static int numberofnodes(Node root)
	{
		if(root==null)
			return 0;
		else
			return(numberofnodes(root.right)+numberofnodes(root.left)+1);
	}
	


void level_elements(Node root,int level)
{
	
	if(root==null)return;
	
	if(level==0)
	{
		
		System.out.println(root.key+" ");
	}
	
	else if(level>0) 
	{
		level_elements(root.left,level-1);
		level_elements(root.right,level-1);
	}
	
}
void height_access()
{
	h=height(root);
	System.out.println("Height of the Binary tree is: "+h);
	}
int height(Node root)
{
	if(root== null)
	{
		return 0;
	}
	else 
	{
		int left_height=height(root.left);
		int right_height=height(root.right);
		if(left_height>right_height)
		{
			return left_height+1;
		}
		else return right_height+1;
	}
	
		}
int largest(Node node)
{   if(node==null)
    {
	System.out.println("null tree");
    }
	while(node.right!=null)
	{
		node=node.right;
	}
	return node.key;
	}
int avl_largest(AVL_Node node)

{   if(node==null)
    {
	System.out.println("null tree");
    }
	while(node.right!=null)
	{
		node=node.right;
	}
	return node.key;
	}
static boolean search_for(Node root,int b)
{

	if(root!=null)
	{
		if(root.key==b)
		{
			return true;
		}
		else
		{
			return search_for(root.left,b) || search_for(root.right,b);
		}
	}return false;
}

static boolean avl_search_for(AVL_Node root1,int a)
{

	if(root1!=null)
	{
		if(root1.key==a)
		{
			return true;
		}
		else
		{
			return avl_search_for(root1.left,a) || avl_search_for(root1.right,a);
		}
	}return false;
}
void inorder(Node root)
{
	if(root!=null)
	{
		inorder(root.left);
		System.out.println(root.key);
		inorder(root.right);
	}
	}
void print_level_elements(Node root)
{
	int h=height(root);
	int p;
	for(p=0;p<=h;p++)
	{   
		level_elements(root,p);
		System.out.println(" ");
	}
	}	
}	

