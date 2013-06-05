
public class Section {
private int crn;
private String sectionNumber="";
private String sectionText="";
private String sectionNotes="";
private String sectionType="";
private String instructor="";
private String availability="";

private static int pixelsPerCharacter=Math.round((SetupClass.screenWidth/6)/47);
private static int spaceRequired=SetupClass.screenWidth/(SetupClass.numOfCourses*2);
static int threshold=(spaceRequired/pixelsPerCharacter)-17;

public Section(int crnNum,String num,String text,String notes,String type,String instr,String avail)	{
	crn=crnNum;
	sectionNumber=cutStringShort(num);
	sectionText=cutStringShort(text==null?"null":text);
	sectionNotes=cutStringShort(notes==null?"null":notes);
	sectionType=cutStringShort(type);
	instructor=cutStringShort(instr);
	availability=cutStringShort(avail);
}



public String getSectionText() {
	return sectionText;
}
public void setSectionText(String sectionText) {
	this.sectionText = sectionText;
}
public String getSectionNotes() {
	return sectionNotes;
}
public void setSectionNotes(String sectionNotes) {
	this.sectionNotes = sectionNotes;
}
public String getSectionType() {
	return sectionType;
}
public void setSectionType(String sectionType) {
	this.sectionType = sectionType;
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public String getAvailability() {
	return availability;
}
public void setAvailability(String availability) {
	this.availability = availability;
}



public int getCrn() {
	return crn;
}



public void setCrn(int crn) {
	this.crn = crn;
}



public String getSectionNumber() {
	return sectionNumber;
}



public void setSectionNumber(String sectionNumber) {
	this.sectionNumber = sectionNumber;
}

public String toString()	{
	return "\n"+"     CRN:"+crn+"\n"+"     Section Number:"+sectionNumber+"\n"+"     Section Text:"+sectionText+"\n"+"     Section Notes:"+sectionNotes+"\n"+"     Section Type:"+sectionType+"\n"+"     Instructors:"+instructor+"\n"+"     Availability:"+availability;
}
	
public static String cutStringShort(String str) {
	//47 characters take up a 6th of the screenwidth. 17 of them MUST be there.
	if(str.length()<threshold) return str;
	return str.substring(0, threshold-3)+"...";
}

}
