#include<stdio.h>
#include <string.h>
char key[1000],pt[1000],ct[1000];
void toUpper(char a[])
{
	int i=0;
	for(i=0;i<strlen(a);i++)
	{
		if(a[i]>=97 && a[i]<=122)
			a[i]=a[i]-32;
	}
}
void generateKey(char key[],char m[][5])
{
	char temp[25];
	int i,j,flag[26],counter=0;
	for (i = 0; i < strlen(key); ++i)
		if(key[i]=='J')
			key[i]='I';
	for(i=0;i<26;i++){
		if(i==9)
		flag[i]=1;
		else
		flag[i]=0;
	}
	for(i=0;i<strlen(key);i++)
		if(flag[key[i]-65]==0){
			temp[counter++]=key[i];
			flag[key[i]-65]=1;
		}
	//printf("%d\n", counter);
	for(i=0;i<26;i++)
		if(flag[i]==0){
			temp[counter++]=(char)(i+65);
		}
	temp[counter]='\0';
	counter=0;
	for(i=0;i<5;i++)
		for(j=0;j<5;j++)
			m[i][j]=temp[counter++];
}
void updatePlainText(char pt[],char npt[])
{
	int i=0,counter=0;
	for (i = 0; i < strlen(pt); ++i)
		if(pt[i]=='J')
			pt[i]='I';
	for(i=0;i<strlen(pt);i+=2)
	{
		if(pt[i]==pt[i+1])
		{
			if(pt[i]=='X'){
				npt[counter++]=pt[i];
				npt[counter++]='Z';
				npt[counter++]=pt[i+1];
			}
			else{
				npt[counter++]=pt[i];
				npt[counter++]='X';
				npt[counter++]=pt[i+1];
			}
		}
		else{
			npt[counter++]=pt[i];
			npt[counter++]=pt[i+1];
		}
	}
	if(counter%2!=0)
		npt[counter++]='X';
	npt[counter]='\0';
	printf("%s\n", npt);
}
void getPos(char m[][5],int pos[][26])
{
	int i,j;
	for(i=0;i<5;i++)
	{
		for(j=0;j<5;j++)
		{
			pos[0][m[i][j]-65]=i;
			pos[1][m[i][j]-65]=j;
		}
	}
}
void encrypt(char pt[],char key[],char ct[])
{
	int i,j,pos[2][26];
	char buff[1000],m[5][5];
	generateKey(key,m);
	updatePlainText(pt,buff);
	getPos(m,pos);
	for (i = 0; i < strlen(buff); i+=2)
	{
		int r1,r2,c1,c2;
		r1 = pos[0][buff[i]-65];
		r2 = pos[0][buff[i+1]-65];
		c1 = pos[1][buff[i]-65];
		c2 = pos[1][buff[i+1]-65];
		if(r1 == r2)
		{
			ct[i]=m[r1][(c1+1)%5];
			ct[i+1]=m[r2][(c2+1)%5];
		}
		else if(c1 == c2)
		{
			ct[i]=m[(r1+1)%5][c1];
			ct[i+1]=m[(r2+1)%5][c2];
		}
		else
		{
			ct[i]=m[r1][c2];
			ct[i+1]=m[r2][c1];
		}
	}
	ct[i]='\0';
}
void decrypt(char ct[],char key[],char pt[])
{
	int i,j,pos[2][26],counter=0;
	char m[5][5],buff[1000];
	generateKey(key,m);
	getPos(m,pos);
	for (i = 0; i < strlen(ct); i+=2)
	{
		int r1,r2,c1,c2;
		r1 = pos[0][ct[i]-65];
		r2 = pos[0][ct[i+1]-65];
		c1 = pos[1][ct[i]-65];
		c2 = pos[1][ct[i+1]-65];
		if(r1 == r2)
		{
			buff[i]=m[r1][(c1+4)%5];
			buff[i+1]=m[r2][(c2+4)%5];
		}
		else if(c1 == c2)
		{
			buff[i]=m[(r1+4)%5][c1];
			buff[i+1]=m[(r2+4)%5][c2];
		}
		else
		{
			buff[i]=m[r1][c2];
			buff[i+1]=m[r2][c1];
		}
	}
	buff[i]='\0';
	pt[counter++]=buff[0];
	for(i=1;i<strlen(buff)-1;i++)
	{
		if(buff[i]=='X' && buff[i-1]==buff[i+1])
		{
			pt[counter++]=buff[i+1];
			i+=2;
		}
		if(buff[i]=='X' && buff[i-1]==buff[i+1] && buff[i-1]=='X')
		{
			pt[counter++]=buff[i+1];
			i+=2;
		}
		else
			pt[counter++]=buff[i];
	}
	if(buff[i]=='X')
		pt[counter]='\0';
	else
		pt[counter+1]='\0';
}
int main()
{
	printf("Enter the key: ");
	scanf("%s",&key);
	toUpper(key);
	printf("Enter the plain text: ");
	scanf("%s",&pt);
	toUpper(pt);
	encrypt(pt,key,ct);
	printf("Cipher text = %s\n",ct);
	printf("Enter the cipher text: ");
	scanf("%s",&ct);
	toUpper(ct);
	decrypt(ct,key,pt);
	printf("Plain text = %s\n",pt);
}