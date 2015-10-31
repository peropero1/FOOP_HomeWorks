import java.util.*;
class Player
{
	  int id; //由id來判斷可取得牌數及抽牌的下一家
	  int status; //1表示贏
	  int card[]; //玩家手牌，長度由id決定
	  //產生玩家
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
	  //Show牌
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
	  //發牌，一副牌不重複
	  public void dealCard(int deck[])
	  {
		  Random rand=new Random();
		  int i=0,j,pos=rand.nextInt(54);
		  if(id <=1)   //p0p1取14張牌，p2p3取13張牌
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
	  //取牌，pi抽下一家的牌
	  public void getCard1(int outputcard[],int inputcard[],int pid,int pid2)
	  {   //inputcard表要被抽牌的玩家(i+1)%4的手牌，outputcard表玩家i的手牌
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
	  //整理手牌,含排序及去掉相同的牌
	  public void  arrangeCard()
	  { 
		  Arrays.sort(this.card);//排序，在java.util裡面
		  int i,j;
		  //抽掉重複的牌
		  //System.out.println("換算結果: ");
		  for(i=0;i<this.card.length-1;i++) //card[i]={0-53}
		  {  
			  j=i+1;
			  if(this.card[i]<55) //還沒抽過的
			  {
				  if((1<this.card[i])&& (this.card[i]<54))
				  { //若為一般牌則抽出，分別設定成55,且三張一樣時需保留花色最大者
					  if((this.card[i]-2)/4==(this.card[j]-2)/4)
					  {
						  this.card[i]=55;
						  this.card[j]=55;
						  ++j;
					  }
				  }
				  else if(this.card[i]==0||this.card[i]==1)
					  ;//若為RJ或BJ時do nothing
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
      //int desktop[]=new int [4]; // 放給另一玩家抽的牌
      int state[]=new int[4];
      //初始化牌組
      for(i=0;i<54;i++)
     	 deck[i]=0;
      //初始化玩家狀態
      for(i=0;i<4;i++)
    	  state[i]=0;
      Player p[]=new Player[4];   //產生玩家
      for(i=0;i<4;i++)
    	  p[i]=new Player(i);
	  System.out.println("Deal Cards:");
      //發牌
      for(i=0;i<4;i++)
      {
    	  p[i].dealCard(deck);
    	  p[i].showCard(p[i].id);
      }
      System.out.println("");
      System.out.println("Drop Cards:");
      //整理手牌(抽掉重複)
      for(i=0;i<4;i++)
      {
    		p[i].arrangeCard();
    		p[i].showCard(p[i].id);
       }
      System.out.println("");
      System.out.println("Game Start:");
      int win=0; //win=3時遊戲結束
      i=0;
      while (win<3) //剩一人手上有牌
      {  
    	  win=0;
    	  for(index=0;index<4;index++)
    		  win+=state[index]; 
    	  if(p[i].remainCard(p[i].card)!=0)
    	  {
    		  for(j=(i+1)%4;j!=i;++j)
    		  {
    			  j=j%4;
    			  if(p[j].remainCard(p[j].card)!=0)//p[j]手上還有牌
    			  {
        	    	  p[i].getCard1(p[i].card,p[j].card,i,j); //getCard(抽牌者，被抽牌者)
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
	
