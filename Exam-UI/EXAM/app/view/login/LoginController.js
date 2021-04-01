
Ext.define('Desktop.view.login.LoginController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.login-login',

	sendRequest: function(actionName, contentType, payload, header) {

		if (Ext.isEmpty(payload)) {
			payload = new Array();
		}

		var request = {
			actionName      : actionName,
			contentType     : contentType,
			requestId       : null,
			requestType     : null,
			header          : header,
			body            : payload,
			message         : null,
			dispatchType    : null,
			sender          : this,
			component       : null,
			onSuccess       : this.onSuccess,
			onLoginFailed   : this.onLoginFailed
		};

		var requestId = nMessageProcessor.sendRequest(request);
	},

	onSuccess: function(request, response) {

	},

	onLoginFailed: function(request, response, statusText) {
		Ext.MessageBox.alert('LOGIN FAILED', "Please Provide Appropriate Credentials");
	},
	onLoginButtonClick: function(button, e, eOpts) {

		var me = this;
		var email = me.lookupReference('email').value;
		var buttonLogin = button;
		buttonLogin.disable();

		if(email == null || email == ""){
			Ext.MessageBox.alert('Error', 'Please enter your Email');
			buttonLogin.enable();
			return;
		}

		var headerInfo;

		if(button.reference.includes('Examer')){
			headerInfo = {
				contentType     : 'Examer',
				actionType      : 'SELECT_BY_EMAIL',
			};
		}
		else{
			headerInfo = {
				contentType     : 'Examiner',
				actionType      : 'SELECT_BY_EMAIL',
			};
		}

		var payLoadInfo = {
			email                : email
		};

		var jsonObj = {
				header  : headerInfo,
				payload : JSON.stringify(payLoadInfo)
		};

		var jsonString = JSON.stringify(jsonObj);

		Ext.Ajax.request({
			url     : LOGIN_URL,
			method  : 'POST',
			jsonData  : jsonString,
			success : function(result, request){

				var message = eval("(" + result.responseText + ")");
				if(message.payload){
					message = eval("(" + message.payload + ")");
					if(message.length > 0){
						loginWindow.close();
						Ext.ux.ActivityMonitor.init({ verbose : false });
						Ext.ux.ActivityMonitor.start();

						gExamer = message[0];
						gExaminer = message[0];
						gRole = message[0].role;
						loginUser = true;

						// DISPLAY DESKTOP ICONS
						app = new Desktop.App();
					}
					else{
						buttonLogin.enable();
						Ext.MessageBox.alert('Login Error',"User Not Found");
					}
				}
				else{
					buttonLogin.enable();
					Ext.MessageBox.alert('Login Error',"User Not Found");
				}
			},
			failure : function(result, request){
				buttonLogin.enable();
				Ext.MessageBox.alert('Server Error',"Login Error");
			}
		});
	},

	onCancelButtonClick: function(button, e, eOpts) {
		this.lookupReference('email').reset();
	}
});
