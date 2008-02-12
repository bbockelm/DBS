import sys
class NvsException(Exception):
	def __init__(self, msg, code=0):
		self.msg = msg
		self.code = code
		def getMessage():
			return self.msg
		def getCode():
			return self.code

