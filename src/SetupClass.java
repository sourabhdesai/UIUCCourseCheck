import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;


public class SetupClass {

	/**
	 * @param args
	 */
	public static int screenHeight;
	public static int screenWidth;
	public static int numOfCourses;
	public static int fontSize=20;
	public static int multiplier=2;
	public static Image screenShot;
	public static Course[] Courses;
	//Blue=110,139,191
	//Orange=239,138,28
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight=((int) screenSize.getHeight()-80)*multiplier;
		screenWidth=((int) screenSize.getWidth())*multiplier;
//		System.out.println(screenWidth);
		Zen.DEFAULT_SIZE=new Dimension(screenWidth,screenHeight);
		Zen.setFont("Arial-"+fontSize);
		Zen.fillRect(0, 0, screenWidth, screenHeight);
		Course[] courses= getCoursesFromTxt();
		Courses=courses;
		numOfCourses=courses.length==0?1:courses.length;
		CourseXMLParser parser= new CourseXMLParser(courses);
		parser.updateCourseSectionInfo();
		courses=parser.getCourses();
		setUpScreen();
		drawCourseInfo(courses);
		Image img=Zen.toImage(PixelEffects.resize(Zen.toRGBArray(Zen.getWindowScreenShot()),screenHeight/multiplier,screenWidth/multiplier));
		screenShot=img;
		Zen.closeWindow();
		Zen.create(screenWidth/multiplier, screenHeight/multiplier, Zen.DEFAULT_OPTIONS);
		Zen.drawImage(img, 0, 0);
		classZoomInSetup();
//		System.out.println("Done!"+numOfCourses);
	}
	
	public static void setUpScreen()	{
		int columnWidth=screenWidth/numOfCourses;
		boolean isBlue=true;
		for(int i=0;i<numOfCourses;i++)	{
			if(isBlue) Zen.setColor(110, 139, 191);
			else Zen.setColor(239,138,28);
			isBlue=!isBlue;
			Zen.fillRect(i*columnWidth,0, columnWidth, screenHeight);
		}
	}
	
	public static void drawCourseInfo(Course[] courses)	{
		Zen.setColor(255,255,255);
		int columnWidth=screenWidth/numOfCourses;
		for(int i=0;i<courses.length;i++) {
			String summarayString=courses[i].SummarayString();
			int index=0;
			int count=0;
			int shiftX=0;
			int shiftY=0;
			while(index != -1)	{
			index=summarayString.indexOf("\n");
			Zen.drawText(summarayString.substring(0, index), (i*columnWidth)+10+shiftX, fontSize+count-shiftY);
			summarayString=summarayString.substring(index+1);
			index=summarayString.indexOf("\n");
			count+=18;
			shiftX=(fontSize+count)>screenHeight ? columnWidth/2:0;
			shiftY=(fontSize+count)>screenHeight ?screenHeight-fontSize:0;
			
			}
		}
//		System.out.println(courses[0].SummarayString());
	}
	
	public static Course[] getCoursesFromTxt()	{
		TextIO.readFile("saved_data_inputs/INPUT COURSES HERE.txt"); //make it "saved_data_inputs/INPUT COURSES HERE.txt"  before exporting
		String line="**";
		while(line.charAt(0)=='*') {
			line=TextIO.getln();
//			System.out.println(line);
		}
		line=line.substring(1).trim();
		String[] coursesTxt=line.split(",");
		Course[] courses= new Course[coursesTxt.length];
		// Example of courses line	CS:125:FA2013,CLCV:115:FA2013,PHYS:214:FA2013
		for(int i=0;i<courses.length;i++)	{
			coursesTxt[i]=coursesTxt[i].trim();
			String[] courseInfo= coursesTxt[i].split(":");
			courses[i]=new Course(courseInfo[0].trim(),Integer.parseInt(courseInfo[1].trim()),courseInfo[2].trim());
		}
		return courses;
	}
	
	public static void classZoomInSetup()	{
		SetupClass.UpdateThread updater=new SetupClass().new UpdateThread();
		updater.start();
		Zen.waitForClick();
		updater.stopRun();
		int x=Zen.getMouseClickX();
		int columnWidth=Zen.getZenWidth()/numOfCourses;
		int index=x/columnWidth;
		if(index%2==0) Zen.setColor(110,139,191);
		else Zen.setColor(239,138,28);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		drawSingleCourseInfo(Courses[x/columnWidth],columnWidth);
		classZoomOutSetup();
	}
	
	public static void classZoomOutSetup()	{
		Zen.waitForClick();
		Zen.drawImage(screenShot, 0, 0);
		classZoomInSetup();
	}
	
	public static void drawSingleCourseInfo(Course course,int columnWidth)	{
		Zen.setColor(255,255,255);
			String summarayString=course.SummarayString();
			int index=0;
			int count=0;
			int shiftX=0;
			int shiftY=0;
			while(index != -1)	{
			index=summarayString.indexOf("\n");
			Zen.drawText(summarayString.substring(0, index), 10+shiftX, fontSize+count-shiftY);
			summarayString=summarayString.substring(index+1);
			index=summarayString.indexOf("\n");
			count+=18;
			shiftX=(fontSize+count)>Zen.getZenHeight() ? columnWidth*((fontSize+count)/Zen.getZenHeight()):0;
			shiftY=(fontSize+count)>Zen.getZenHeight() ?(Zen.getZenHeight())*((fontSize+count)/Zen.getZenHeight())-fontSize:0;
			}
//		System.out.println(courses[0].SummarayString());
	}
	
	public static int map(int x, int in_min, int in_max, int out_min, int out_max)	{
		if(in_max== in_min) return out_max;
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	class UpdateThread extends Thread	{
		private static final long updateTime=5*(60*1000)/2;
		long currentTime;
		boolean isRunning=true;
		
		public void run()	{
			currentTime=System.currentTimeMillis();
			long timeForUpdate=this.currentTime+updateTime;
			while(timeForUpdate>System.currentTimeMillis())	{
				if(!isRunning) return;
				//System.out.println("hi");
			}
			SetupClass.main(null);
			return;
		}
		
		public void stopRun()	{
			isRunning=false;
		}
		
	}

}
