package cinema;
import java.util.*;

import static java.lang.Math.round;

public class Cinema {
   public class Seat{
        char status;
        int price;
        Seat(char status,int price){
            this.status=status;
            this.price=price;
        }
        public void setStatus(char status){
            this.status=status;
        }
        public void setPrice(int price){
            this.price=price;
        }
        public int getPrice(){
            return this.price;
        }
        public char getStatus(){
            return this.status;
        }
    }
    public int row,col,seats,income=0,buyedSeats,currentIncome;
    public double percentageBuyed;
    public Seat[][] hall;
    Cinema(int x,int y){
        this.row=x;
        this.col=y;
        this.seats=this.row*this.col;
        this.buyedSeats=0;
        this.currentIncome=0;
        this.percentageBuyed=0.00d;
        hall=new Seat[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                hall[i][j]=new Seat('S',0);
                if(seats<=60){
                    hall[i][j].setPrice(10);
                    this.income+=10;
                }
                else{
                    int fr=row/2;
                    if(i<fr) {
                        hall[i][j].setPrice(10);
                        this.income+=10;
                    }
                    else {
                        hall[i][j].setPrice(8);
                        this.income+=8;
                    }
                }
            }
        }
    }
    public void menu(){
        while(true){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            Scanner sc=new Scanner(System.in);
            int choice=sc.nextInt();
            switch(choice){
                case 1: printSeats();
                        break;
                case 2: buySeat();
                        break;
                case 3: showStatistics();
                        break;
                case 0: return;
                default:
            }
        }
    }
    public void printSeats(){
        System.out.println("Cinema: ");
        System.out.print("  ");
        for(int j=0;j<col;j++){
            System.out.print((j+1)+" ");
        }
        System.out.println();
        for(int i=0;i<row;i++){
            System.out.print((i+1)+" ");
            for(int j=0;j<col;j++){
                System.out.print(hall[i][j].getStatus()+" ");
            }
            System.out.println();
        }
    }
    public void showStatistics(){
        System.out.println("Number of purchased tickets: "+this.buyedSeats);
        System.out.println("Percentage: "+String.format("%.2f", this.percentageBuyed)+"%");
        System.out.println("Current income: $"+this.currentIncome);
        System.out.println("Total income: $"+this.income);
    }
    public void buySeat(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a row number:");
            int x = sc.nextInt();
            x--;
            System.out.println("Enter a seat number in that row:");
            int y = sc.nextInt();
            y--;
            if (x >= this.row || x < 0 || y < 0 || y >= this.col) {
                System.out.println("Wrong input!");
            } else if (this.hall[x][y].getStatus() == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                System.out.println("Ticket price: $" + this.hall[x][y].getPrice());
                this.hall[x][y].setStatus('B');
                this.buyedSeats++;
                this.currentIncome+=this.hall[x][y].getPrice();
                this.percentageBuyed=(this.buyedSeats*100.0d/this.seats);
                return;
            }
        }
    }
    public int getSeatPrice(int row,int col){
        return this.hall[row][col].getPrice();
    }
    public int calculateIncome(){
        return this.income;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int r=sc.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int c=sc.nextInt();
        Cinema cin=new Cinema(r,c);
        /*int pr=cin.calculateIncome();
        System.out.println("Total income: ");
        System.out.println("$"+pr);*/
        cin.menu();
    }
}