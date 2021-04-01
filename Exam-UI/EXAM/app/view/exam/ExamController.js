var loanPanel = null;
Ext.define('Desktop.view.question.ExamController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.examController',

	// rcv request
	onSuccess: function (request, response) {
		var controller = request.sender;
		var headerRef = request.header.reference;
		var data = eval("(" + response.payload + ")");

		if (isMessageBox) {
			Ext.MessageBox.hide();
			isMessageBox = false;
		}

		console.log(response);

		if (headerRef == 'loadExamViewGridData') {
			loadDataInGlobalStore(data, 'gExamStore');
		}
	},

	// send json request
	sendRequest: function (actionName, contentType, payload, header) {

		header.appName = gAppName;
		header.envId = gEnvId;
		header.senderId = loginUser.id;
		header.destination = SERVER_URL;

		var request = {
			actionName: actionName,
			contentType: contentType,
			requestId: null,
			requestType: null,
			header: header,
			body: payload,
			message: null,
			dispatchType: null,
			sender: this,
			onSuccess: this.onSuccess,
			onError: this.onError,
			onStatusUpdate: this.onStatusUpdate
		};

		var requestId = nMessageProcessor.sendRequest(request);
	},

	onActivateExamPanel: function (cmp, eOpts) {

		this.loadExamViewGridData(this);			
	},

	loadExamViewGridData: function(cmp){
		var header = {
			reference: 'loadExamViewGridData'
		};

		this.sendRequest('SELECT_ALL', 'Exam', null, header);
	},
});