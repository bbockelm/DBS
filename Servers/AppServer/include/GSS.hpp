#ifndef _GSS_hpp_included_
#define _GSS_hpp_included_
#define GSS_SUCCESS                          1
#define GSS_MAX_MESSAGE_LENGTH 1024


#include "globus_gss_assist.h"
#include "Socket.hpp"
#include <string>
#include <openssl/x509.h>


class  GSS {
	private:
		OM_uint32 major_status;
		OM_uint32 minor_status;
		gss_ctx_id_t global_context_handle;
		gss_cred_id_t credential_handle;
		std::string dn;
		int token_status;
		FILE * socket_in_out_file;

		void displayMessage(std::string message);
		int activate();
		int acquireCredentials();
		void releaseCredential();
		void deleteContext();
	public:
		GSS();
		~GSS();
		int cOpen(int des);
		int sOpen(int des);
		int send(std::string message);
		std::string recv();
		void recv(char* data);
		std::string getDN();
		void close();
};
#endif

