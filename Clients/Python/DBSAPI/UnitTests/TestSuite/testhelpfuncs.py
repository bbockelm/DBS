import unittest
import xml.sax, xml.sax.handler
from xml.sax.saxutils import escape
from xml.sax import SAXParseException

def assertAlgo(test, algoIn1, algoIn2):
        test.assertEqual(algoIn1['ExecutableName'], algoIn2['ExecutableName'])
        test.assertEqual(algoIn1['ApplicationVersion'], algoIn2['ApplicationVersion'])
        test.assertEqual(algoIn1['ApplicationFamily'], algoIn2['ApplicationFamily'])
        test.assertEqual(algoIn1['ParameterSetID']['Hash'], algoIn2['ParameterSetID']['Hash'])

def assertAlgoPS(test, algoIn1, algoIn2):
        assertAlgo(test, algoIn1, algoIn2)
        test.assertEqual(algoIn1['ParameterSetID']['Hash'], algoIn2['ParameterSetID']['Hash'])
        test.assertEqual(algoIn1['ParameterSetID']['Name'], algoIn2['ParameterSetID']['Name'])
        test.assertEqual(algoIn1['ParameterSetID']['Version'], algoIn2['ParameterSetID']['Version'])
        test.assertEqual(algoIn1['ParameterSetID']['Type'], algoIn2['ParameterSetID']['Type'])
        test.assertEqual(algoIn1['ParameterSetID']['Annotation'], algoIn2['ParameterSetID']['Annotation'])
        test.assertEqual(algoIn1['ParameterSetID']['Content'], algoIn2['ParameterSetID']['Content'])

def assertProc(test, procIn1, procIn2):
        test.assertEqual(procIn1['Name'], procIn2['Name'])
        test.assertEqual(procIn1['PrimaryDataset']['Name'], procIn2['PrimaryDataset']['Name'])
        test.assertEqual(procIn1['AcquisitionEra'], procIn2['AcquisitionEra'])
        test.assertEqual(procIn1['GlobalTag'], procIn2['GlobalTag'])
        test.assertEqual(procIn1['PhysicsGroup'], procIn2['PhysicsGroup'])
        test.assertEqual(procIn1['Status'], procIn2['Status'])
        test.assertEqual(procIn1['XtCrossSection'], procIn2['XtCrossSection'])

def assertRun(test, runIn1, runIn2):
        test.assertEqual(runIn1['RunNumber'], runIn2['RunNumber'])
        test.assertEqual(runIn1['NumberOfEvents'], runIn2['NumberOfEvents'])
        test.assertEqual(runIn1['NumberOfLumiSections'], runIn2['NumberOfLumiSections'])
        test.assertEqual(runIn1['TotalLuminosity'], runIn2['TotalLuminosity'])
        test.assertEqual(runIn1['StoreNumber'], runIn2['StoreNumber'])
        test.assertEqual(runIn1['StartOfRun'], runIn2['StartOfRun'])
        test.assertEqual(runIn1['EndOfRun'], runIn2['EndOfRun'])

def assertFile(test, fileIn1, fileIn2):
        test.assertEqual(fileIn1['Checksum'], fileIn2['Checksum'])
        test.assertEqual(fileIn1['Adler32'], fileIn2['Adler32'])
        test.assertEqual(fileIn1['Md5'], fileIn2['Md5'])
        test.assertEqual(fileIn1['LogicalFileName'], fileIn2['LogicalFileName'])
        test.assertEqual(fileIn1['NumberOfEvents'], fileIn2['NumberOfEvents'])
        test.assertEqual(fileIn1['FileSize'], fileIn2['FileSize'])
        test.assertEqual(fileIn1['Status'], fileIn2['Status'])
        #test.assertEqual(fileIn1['ValidationStatus'], fileIn2['ValidationStatus'])
        test.assertEqual(fileIn1['FileType'], fileIn2['FileType'])
        test.assertEqual(fileIn1['AutoCrossSection'], fileIn2['AutoCrossSection'])

def assertLumi(test, lumiIn1, lumiIn2):
        test.assertEqual(lumiIn1['LumiSectionNumber'], lumiIn2['LumiSectionNumber'])
        test.assertEqual(lumiIn1['StartEventNumber'], lumiIn2['StartEventNumber'])
        test.assertEqual(lumiIn1['EndEventNumber'], lumiIn2['EndEventNumber'])
        test.assertEqual(lumiIn1['LumiStartTime'], lumiIn2['LumiStartTime'])
        test.assertEqual(lumiIn1['LumiEndTime'], lumiIn2['LumiEndTime'])
        test.assertEqual(lumiIn1['RunNumber'], lumiIn2['RunNumber'])

def assertBlock(test, block1, block2):
        test.assertEqual(block1['Name'], block2['Name'])


def getDataFromDBSServer(api, userInput, qu="exe"):

        listresults=[]
        count=0

        data=api.executeQuery(userInput, type=qu)
        try:
        # DbsExecutionError message would arrive in XML, if any
         class Handler (xml.sax.handler.ContentHandler):

           def __init__(self):
                xml.sax.handler.ContentHandler.__init__(self)
                count=0
           def startElement(self, name, attrs):

                if name == 'result':
                        results={}
                        for akey in attrs.keys():
                                results[str(akey).upper()]=str(attrs[akey])
                        listresults.append(results)
                pass
         xml.sax.parseString (data, Handler ())
        except SAXParseException, ex:
         msg = "Unable to parse XML response from DBS Server"
         msg += "\n  Server not responding as desired %s" % self.Url
         raise DbsConnectionError (args=msg, code="505")

        return listresults

