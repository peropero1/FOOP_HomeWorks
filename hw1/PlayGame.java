import java.util.*;
class Player
{
	  int id; //��id�ӧP�_�i���o�P�ƤΩ�P���U�@�a
	  int status; //1���Ĺ
	  int card[]; //���a��P�A���ץ�id�M�w
	  //���ͪ��a
	  Player(int id)
	  {
		  this.id=id;
		  this.status=0;
	  }
	  
	  public int remainCard(int cardin[])
	  {
		  int count=0,i;
		  for(i=0;i<cardin.length;i++)
		  {
			  if(cardin[i]==55)
				  count++;
		  }
		  return (card.length-count);
	  }
	  //Show�P
	  public void showCard(int pid)
	  {
    	  System.out.print("Player"+pid+":");
		  for(int i=0;i<this.card.length;i++)
		  {
			  if(this.card[i]<54 && this.card[i]>1)
			  {
				  int suit=(this.card[i]-2)%4;
				  int value=(this.card[i]-2)/4;
				  switch(suit)
				  {
				  	case 0:
					  System.out.printf("C");
					  break;
				  	case 1:
					  System.out.printf("D");
					  break;
				  	case 2:
					  System.out.printf("H");
					  break;
				  	case 3:
					  System.out.printf("S");
					  break;
				  }
				  switch(value)
				  {
				  	case 0:
					  System.out.printf("2");
					  break;
				  	case 1:
					  System.out.printf("3");
					  break;
				  	case 2:
					  System.out.printf("4");
					  break;
				  	case 3:
					  System.out.printf("5");
					  break;
				  	case 4:
					  System.out.printf("6");
					  break;
				  	case 5:
					  System.out.printf("7");
					  break;
				  	case 6:
					  System.out.printf("8");
					  break;
				  	case 7:
					  System.out.printf("9");
					  break;
				  	case 8:
					  System.out.printf("10");
					  break;
				  	case 9:
					  System.out.printf("J");
					  break;
				  	case 10:
					  System.out.printf("Q");
					  break;
				  	case 11:
					  System.out.printf("K");
					  break;
				  	case 12:
					  System.out.printf("A");
					  break;
				  }
				  System.out.printf(" ");
			  }
			else if(this.card[i]==0)
					System.out.printf("RJ ");
			else if (this.card[i]==1)
					System.out.printf("BJ ");
			else
				;

		  }
		  //System.out.printf(" ");
	   System.out.println("");
	  }
	  //�o�P�A�@�ƵP������
	  public void dealCard(int deck[])
	  {
		  Random rand=new Random();
		  int i=0,j,pos=rand.nextInt(54);
		  if(id <=1)   //p0p1��14�i�P�Ap2p3��13�i�P
			i=14;
		  else if(id>1)
			i=13;
		  this.card=new int [i];
		  for(j=0;j<this.card.length; j++)
			{
			  while(deck[pos]==1)
				  pos=rand.nextInt(54);
			  this.card[j]=pos;
			  deck[pos]=1;
			}
		}
	  //���P�Api��U�@�a���P
	  public void getCard1(int outputcard[],int inputcard[],int pid,int pid2)
	  {   //inputcard��n�Q��P�����a(i+1)%4����P�Aoutputcard���ai����P
		  Random rand=new Random();
		  int tmp=0,i;
		  int pos=rand.nextInt(inputcard.length);
		  while (inputcard[pos]==55)
			  pos=rand.nextInt(inputcard.length);
		  for(i=0;i<card.length;i++)
		  {
			  if(card[i]==55)
			  {
				  tmp=inputcard[pos];
				  outputcard[i]=tmp;
				  inputcard[pos]=55;
				  break;
			  }
		  }
		 System.out.println("Player"+pid+" draws :"+tmp+" from Player"+pid2);
	  }
	  //��z��P,�t�ƧǤΥh���ۦP���P
	  public void  arrangeCard()
	  { 
		  Arrays.sort(this.card);//�ƧǡA�bjava.util�̭�
		  int i,j;
		  //�ⱼ���ƪ��P
		  //System.out.println("���⵲�G: ");
		  for(i=0;i<this.card.length-1;i++) //card[i]={0-53}
		  {  
			  j=i+1;
			  if(this.card[i]<55) //�٨S��L��
			  {
				  if((1<this.card[i])&& (this.card[i]<54))
				  { //�Y���@��P�h��X�A���O�]�w��55,�B�T�i�@�ˮɻݫO�d���̤j��
					  if((this.card[i]-2)/4==(this.card[j]-2)/4)
					  {
						  this.card[i]=55;
						  this.card[j]=55;
						  ++j;
					  }
				  }
				  else if(this.card[i]==0||this.card[i]==1)
					  ;//�Y��RJ��BJ��do nothing
			  }
		   }
	   }
	  
}
public class PlayGame
{
	public static void main (String[] argv)
	{
      int i,j,k=0,index=0;
      int deck[]=new int[54];
      //int desktop[]=new int [4]; // �񵹥t�@���a�⪺�P
      int state[]=new int[4];
      //��l�ƵP��
      for(i=0;i<54;i++)
     	 deck[i]=0;
      //��l�ƪ��a���A
      for(i=0;i<4;i++)
    	  state[i]=0;
      Player p[]=new Player[4];   //���ͪ��a
      for(i=0;i<4;i++)
    	  p[i]=new Player(i);
	  System.out.println("Deal Cards:");
      //�o�P
      for(i=0;i<4;i++)
      {
    	  p[i].dealCard(deck);
    	  p[i].showCard(p[i].id);
      }
      System.out.println("");
      System.out.println("Drop Cards:");
      //��z��P(�ⱼ����)
      for(i=0;i<4;i++)
      {
    		p[i].arrangeCard();
    		p[i].showCard(p[i].id);
       }
      System.out.println("");
      System.out.println("Game Start:");
      int win=0; //win=3�ɹC������
      i=0;
      while (win<3) //�Ѥ@�H��W���P
      {  
    	  win=0;
    	  for(index=0;index<4;index++)
    		  win+=state[index]; 
    	  if(p[i].remainCard(p[i].card)!=0)
    	  {
    		  for(j=(i+1)%4;j!=i;++j)
    		  {
    			  j=j%4;
    			  if(p[j].remainCard(p[j].card)!=0)//p[j]��W�٦��P
    			  {
        	    	  p[i].getCard1(p[i].card,p[j].card,i,j); //getCard(��P�̡A�Q��P��)
        	    	  p[i].arrangeCard(); 
        	    	  p[i].showCard(p[i].id);
        	    	  break;
    			  } 				
    		  }
	    	  i=(i+1)%4;
    	  }
    	  else
    	  {
    		 state[i]=1;
    		 System.out.println("Player"+i+" Wins!!!");
    		 i=(i+1)%4;
    	  }
      }
      System.out.println("Game Over!!");
	}
}
	
