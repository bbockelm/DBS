#include "GSS.hpp"
#include "GSSException.hpp"

#include <iostream>
#include <fstream>
#include <ios>
#include <string>

using namespace std;

GSS::GSS() {
	major_status = 0;
	minor_status = 0;
	global_context_handle = GSS_C_NO_CONTEXT;
	credential_handle = GSS_C_NO_CREDENTIAL;
	token_status = 0;
	this->activate();
	this->acquireCredentials();
}

GSS::~GSS(){
	cout<<"inside destructor"<<endl;
	releaseCredential();
	cout<<"Done releaseCredential inside destructor"<<endl;
}


int GSS::activate(){
	if(globus_module_activate(GLOBUS_GSI_GSS_ASSIST_MODULE) != GLOBUS_SUCCESS){
		throw GSSException("ERROR: Can not activate globus");
	}
	return(GSS_SUCCESS);
}

int GSS::acquireCredentials(){
	/*major_status = gss_acquire_cred(
			 &minor_status,
			 GSS_C_NO_NAME,
			 GSS_C_INDEFINITE,
			 GSS_C_NO_OID_SET,
			 GSS_C_INITIATE,
			 &credential_handle,
			 NULL,
			 NULL);*/
	major_status = globus_gss_assist_acquire_cred(&minor_status,
							GSS_C_INITIATE,
							&credential_handle);
							
	if (major_status != GSS_S_COMPLETE){
		throw GSSException("ERROR: Can not aquire crendentials");
	}
	return(GSS_SUCCESS);
	
}

void GSS::close() {
	cout<<"closing socket "<<endl;
	fclose(this->socket_in_out_file);
	cout<<"deleting context deleteContext "<<endl;
	this->deleteContext();
	//cout<<"done deleteContext "<<endl;
}

int GSS::cOpen(int des){
	this->socket_in_out_file = fdopen(des,"r+");
	//cout<<"this->socket_in_out_file is "<<this->socket_in_out_file<<endl;
	OM_uint32 ret_flags = 0;
	major_status = globus_gss_assist_init_sec_context(&this->minor_status,
							this->credential_handle,
							&this->global_context_handle,
							"GSI-NO-TARGET",
							//GSS_C_DELEG_FLAG|GSS_C_MUTUAL_FLAG,
							GSS_C_MUTUAL_FLAG,
							&ret_flags,
							&this->token_status,
							globus_gss_assist_token_get_fd,
							(void *) this->socket_in_out_file,
							globus_gss_assist_token_send_fd,
							(void *) this->socket_in_out_file);
	      
	if(major_status != GSS_S_COMPLETE){
		throw GSSException("ERROR: Handshake Failed\nmajor_status != GSS_S_COMPLETE");
	}
	return(GSS_SUCCESS);
}	

int GSS::sOpen(int des){
	this->socket_in_out_file = fdopen(des,"r+");
	OM_uint32 ret_flags=0;
	char * clientName;

	major_status = globus_gss_assist_accept_sec_context(&minor_status,
							&this->global_context_handle,
							this->credential_handle,
							&clientName,
							&ret_flags,
							NULL,
							&this->token_status,
							NULL,
							globus_gss_assist_token_get_fd,
							(void *) this->socket_in_out_file,
							globus_gss_assist_token_send_fd,
							(void *) this->socket_in_out_file);
	      
	if(major_status != GSS_S_COMPLETE){
		delete clientName;
		throw GSSException("ERROR: Handshake Failed\nmajor_status != GSS_S_COMPLETE");
	}
	//cout<<"ClientName is "<<clientName<<endl;
	this->dn = (string ) clientName;
	delete clientName;
	return(GSS_SUCCESS);
}	

int GSS::send(string message){
	//cout<<"sending message length is "<<message.length()<<endl;
	//int index = 0;
	//while(index < len) {
	//string message = message1.substr(0, 100);
	
	major_status = globus_gss_assist_wrap_send(&this->minor_status,
						this->global_context_handle,
						(char*)message.c_str(),
						message.length(),
						&this->token_status,
						globus_gss_assist_token_send_fd,
						(void *) this->socket_in_out_file,
						stderr);
	//}
	//cout<<"Done sending message"<<endl;
	if(major_status != GSS_S_COMPLETE){
		throw GSSException("ERROR: GSS Assist Send wrap failure");
	}
	return(GSS_SUCCESS);
}

string GSS::recv(){
//void GSS::recv(char* message){
	//cout<<"line1"<<endl;
	char* data;
	size_t length;
	//cout<<"line2"<<endl;
	major_status = globus_gss_assist_get_unwrap(&this->minor_status,
						this->global_context_handle,
						&data,
						&length,
						&this->token_status,
						globus_gss_assist_token_get_fd,
						(void *) this->socket_in_out_file,
						stderr);
	//cout<<"line3"<<endl;
	if(major_status != GSS_S_COMPLETE){
		delete data;
		throw GSSException("ERROR: GSS Assist Get Unwrap failure");
	}
	//cout<<"line4"<<endl;
        if(length >= GSS_MAX_MESSAGE_LENGTH) {
		delete data;
		//cout<<"ERROR: GSS Assist Get Unwrap returned message too long:"<<length<<"chars >= "<<GSS_MAX_MESSAGE_LENGTH<<" (max allowed)"<<endl;
		throw GSSException("ERROR: GSS Assist Get Unwrap returned message too long");

        }
	//strncpy(message,data,length);
	//message[length]='\0';
	char temp[length];
	strncpy(temp,data,length);
	temp[length]='\0';
	//data[length] = '\0';
	//cout<<"data in recv is "<<data<<endl;
	//string toReturn = (string)message;
	//cout<<"message in recv is "<<message<<endl;
	string toReturn = (string)temp;
	//cout<<"toReturn in recv is "<<toReturn<<endl;
 	//string toReturn = (string)data;
	//ifstream infile((char*)data);
	//getline(infile,toReturn);
	//cout<<"length is "<<length<<endl;
	
	//char temp[length];
	/*for(int i =0 ; i != length; ++i ){
		temp[i] ='\0';
	}
	for(int i =0 ; i != length; ++i ){
		temp[i] = data[i];
		cout<<temp<<endl;
	}
	temp[length] = '\0';*/

	//string toReturn = (string)data;
        /*string toReturn(data);
        cout << "CRAP: " << toReturn <<endl;*/
 	//string toReturn = (string)temp;
	/*string toReturn = "";
	for(int i =0 ; i != length; ++i ){
		toReturn += data[i];
		cout<<toReturn<<endl;
	}*/
	//cout<<"line7"<<endl;
	delete data;
	return(toReturn);
}

string GSS::getDN(){
	return this->dn;
}
    
void GSS::deleteContext(){
	major_status = gss_delete_sec_context(&minor_status,
					&global_context_handle,
					GSS_C_NO_BUFFER);
}

void GSS::releaseCredential(){
	major_status = gss_release_cred(&major_status,
					&credential_handle);
}

