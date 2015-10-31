import java.util.*;

class Card
{
	public static void showCard(int playCard[])	//將丟進來的牌轉成(suit,face)的型式output
	{
		  for(int i=0;i<playCard.length;i++)
		  {
			  System.out.printf("(%c)",97+i);
			  int suit=(playCard[i])%4;
			  int face=(playCard[i])/4;
			  switch(suit)
			  {
				  	case 0:
					  System.out.print("C");	
					  break;
				  	case 1:
					  System.out.print("D");	
					  break;
				  	case 2:
					  System.out.print("H");	
					  break;
				  	case 3:
					  System.out.print("S");	
					  break;
				  }
			  switch(face)
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
	}
}
class player
{
	int handCard[];	//玩家的手牌
	int playerMoney;	//玩家的錢
	String name;
	player(String playerName)	//玩家的建構子
	{
		handCard=new int [5];
		for(int i=0;i<handCard.length;i++)
			handCard[i]=99;
		playerMoney=1000;
		name=playerName;
	}
	//保留要的牌
	public void keepCard(int playCard[],int deck[],String choose)
	{
		if(choose.equals("q")|| choose.equals("abcde"))
			;	//全部保留
		else if(choose.equals("x"))	//全部重發
		{
			for(int i=0;i<playCard.length;i++)
			{
				deck[playCard[i]]=0;
				playCard[i]=99;
			}
		}
		else	//保留部份
		{
			char playerChoose[]=choose.toCharArray();
			int j[]=new int[playerChoose.length];
			for(int i=0;i<playerChoose.length;i++)
			{
				 j[i]=Character.getNumericValue(playerChoose[i]);
				 j[i]-=10;	//a=10
			}

			Arrays.sort(j); //由小排到大
			int i=0,k=0;
			while(i<(j.length) && k<playCard.length)
			{
				if(k!=j[i])	
				{
					deck[playCard[k]]=0;
					playCard[k]=99;
					k++;
				}
				else
				{	
					k++;
					i++;
				}
			}
			if(k==5)	//表示玩家要keep到e, e以前的已經全部設定完成,so do nothing.
				;
			else
			{
				for(int m=(j[i-1]+1);m<playCard.length;m++)	//從輸入的最後一張的下一個開始全部重發
				{	//例：user輸入bc，表示從c以後的都要重發
					deck[playCard[m]]=0;
					playCard[m]=99;
				}
			}

		}

	}
}

class Computer
{
	//發牌
	public static void dealCard(int playCard[],int deck[])//玩家手牌,原始的那疊牌
	{
		Random rand=new Random();
		int i,pos=rand.nextInt(52);
		for(i=0;i<playCard.length;i++)
		{
			if(playCard[i]==99)
			{
				while(deck[pos]==1)
					pos=rand.nextInt(52);
				playCard[i]=pos;
				deck[pos]=1;
			}
			pos=rand.nextInt(52);
		  }
		  Arrays.sort(playCard);//由小排到大
	}
	//發錢
	public static int drawMoney(int cardType, int base,int pMoney) //牌型代碼,賭注base,玩家的錢
	{
		if(base==5 && cardType==250)
			pMoney+=4000;
		else
			pMoney+=cardType*base;
		System.out.printf("The payoff is %d\n",cardType*base);
		return pMoney;
	}
	//判斷牌型
	public static int judgeCard(int handCard[]) //玩家的手牌
	{
	    int flush=0, straight=0, fourKind=0, threeKind=0, i;
	    int pair=0, count=0, royal=0, start=0, end=0;
	    int suit[]=new int[4];	//累計各花色的數量
	    int face[]=new int[13];	//累計數值的數量
	    //face[]的順序同deck[]由2~A
	    //ex:丟入的牌為S5 C9 CK HK SK (15,28,44,46,47)
	    //則suit(2,0,1,2)分別表示C,D,H,S
	    //同原牌組順序2,3,........ ,9,10,J,Q,K,A
	    //      face(0,0,0,1,0,0,0,1, 0,0,0,3,0)
	    for(i=0;i<handCard.length;i++)
	    {
	        suit[handCard[i]%4]++;	//handCard[i[的花色
	        face[handCard[i]/4]++;	//handCard[i]的值
	    }
	    for(i=0;i<suit.length;i++)
	    {
	    	if(suit[i]==5)	//同花
	    		flush++;
	    }
	    for(i=0;i<face.length;i++)
	    {
	        if(face[i]!=0)	//check玩家手上牌的值
	        {
	            end=i;	//for結束時end會在手牌最大值處
	            if(face[i]==4)	//相同的值有四個
	                fourKind=1;
	            else if(face[i]==3)
	            	threeKind=1;
	            else if(face[i]==2)
	                pair++;
	            else if(face[i]==1) //手牌均為單張
	                count ++;
	        }
	    }
	    //check straight
	    if(count==5)	//五張都不一樣
	    {
	        //(10,J,Q,K,A)
	    	if(face[8]==1 && face[9]==1 && face[10]==1&& face[11]==1 &&face[12]==1)
	    		royal= 1; 
	    	//(2,3,4,5,A)
	        else if(face[0]==1 && face[1]==1 && face[2]==1&& face[3]==1 &&face[12]==1)
	        	straight=1; 
	        else 
	        {
	            start=end-count+1;	//check五張是否為straight
	            for(i=start;i<=end;i++)
	            {
	            	if(face[i]==1)
	            		count--;
	            }
	            if(count==0)	//run過5個回合把count歸零表示必為straight
	                straight=1;
	        }
	    }
	    //考慮到2 pair 的情況，pair=2，所以判斷條件用!=0
	    if(royal!=0 && flush!=0)
	        return 250;	//royal flush
	    else if(straight!=0 && flush!=0)
	        return 50;	//straight flush
	    else if(fourKind!=0)
	        return 25;	//4 of a kind
	    else if(threeKind!=0 && pair!=0)
	        return 9;	//full house
	    else if(flush!=0)
	        return 6;	//flush
	    else if(straight!=0 || royal!=0)
	        return 4;	//straight
	    else if(threeKind!=0)
	        return 3; //3 of a kind
	    else if(pair==2)		//2 pairs
    		 return 2;
	    else if(pair==1 && (face[9]>=2 || face[10]>=2 || face[11]>=2 || face[12]>=2))	//Jacks or better	    	
    		return 1;
	    else 
	    	return 0;
	}
}
public class POOCasino 
{
	public static void main (String[] argv) 
	{
		int i,inbet=99,round=1;
	    int deck[]=new int[52];
	    //初始化牌組
	    for(i=0;i<52;i++)
	    	deck[i]=0;
	    //遊戲開始
		System.out.println("PXXCasino Jacks or better, written by p04922003 Yen-Cheng Lai");
	    System.out.print("Game Start, Please enter your name: ");
		Scanner input=new Scanner(System.in);	//名字、錢
		Scanner input2=new Scanner(System.in);	//要keep的牌
		String name=input.next();
		//產生玩家
		player p=new player(name); //初始值為99
		//取牌
		//Computer.dealCard(p.handCard,deck);
		System.out.println("Welcome "+p.name+", you got "+p.playerMoney+" P-dolars!!");
		while(inbet!=0&&(p.playerMoney>0)) 
		{	//當下注金額>0且玩家錢>0時遊戲繼續
			System.out.printf("Throw coins you want to bet for round %d:\n",round);
			System.out.println("(1-5 to bet and 0 to quit)");
			inbet=input.nextInt();
			Computer.dealCard(p.handCard,deck);
			if(0<inbet&&inbet<=5)
			{
				++round;
				p.playerMoney-=inbet;	//扣錢
				System.out.print("You got cards: ");
				Card.showCard(p.handCard);
				System.out.println(" ");
				//選擇要保留的手牌
				System.out.println("Which cards you \"want keep\":");
				System.out.println("(Type \"abcde\" to keep each and \"q\"to hold all)");
				System.out.println("(x to re-deal all)");
				String choose=input2.next();	//player輸入的字串
				p.keepCard(p.handCard,deck,choose);
				Computer.dealCard(p.handCard,deck);
				//
				System.out.print("Your new cards are: ");
				Card.showCard(p.handCard);
				System.out.println(" ");
				System.out.print("You got a ");
				switch(Computer.judgeCard(p.handCard))
				{
					case 0:
						System.out.println("nothing hand.");
						break;
					case 1:
						System.out.println("Jacks or Better hand.");
						break;
					case 2:
						System.out.println("Two pair hand.");
						break;
					case 3:
						System.out.println("Three of a kind hand.");
						break;
					case 4:
						System.out.println("Straight hand.");
						break;
					case 6:
						System.out.println("Flush hand.");
						break;
					case 9:
						System.out.println("Full House hand.");
						break;
					case 25:
						System.out.println("Four of a kind hand.");
						break;
					case 50:
						System.out.println("Straight flush hand.");
						break;
					case 250:
						System.out.println("Royal flush hand.");
						break;
				}
				p.playerMoney=Computer.drawMoney(Computer.judgeCard(p.handCard),inbet,p.playerMoney);
				System.out.println("You have "+p.playerMoney+" P-dollars now");
			}
			else if(inbet==0)
				break;
			else
			{
				System.out.println("(1-5 to bet and 0 to quit)");
				continue;
			}
			//清空玩家手牌
			for(i=0;i<p.handCard.length;i++)
			{
				deck[p.handCard[i]]=0;
				p.handCard[i]=99;
			}

		}
		System.out.print("Good Bye"+p.name+". you play for "+round+" round");
		System.out.printf(" and have "+p.playerMoney+" now");
		input.close();
		input2.close();
	}

}

