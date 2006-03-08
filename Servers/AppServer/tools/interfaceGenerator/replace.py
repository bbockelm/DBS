import os
def changeFile(toReplace):
	fromFile = "Service_services_types.py"
	toFile = "tmp_types.py"
	origFileLines = open(fromFile).readlines()
	newFile = open(toFile, "w")
	for aLine in origFileLines:
		newLine = replace(aLine , toReplace)
		if newLine != None:
			newFile.write(newLine + "\n")
		else:
			newFile.write(aLine)
	newFile.close()
	os.popen('mv '+toFile + ' ' + fromFile)	


def replace(aLine, toReplace):
	foundHere = aLine.find(toReplace)     
	if foundHere != -1 :
		lineSeg1 = aLine[:foundHere]
		lineSeg2 = aLine[foundHere+len(toReplace):]
		elsePart = '            else:\n'    
		elsePart+= '   ' + lineSeg1 + lineSeg2
		ifpart = '            if "something" not in kw :\n    '+lineSeg1+toReplace[:-1]+', something="")'+lineSeg2
		#print ifpart
		#print elsePart
		return ifpart + elsePart
			
			
#changeFile(', ns1.PrimaryDataset_Def(name="parent", ns=ns, optional=1)')
#changeFile(', ns1.ProcessingPath_Def(name="parent", ns=ns, optional=1)')
#changeFile(', ns1.DBS_EventCollection_Def(name="parent", ns=ns, optional=1)')
changeFile(', ns1.EventCollection_Def(name="parent", ns=ns, optional=1)')
