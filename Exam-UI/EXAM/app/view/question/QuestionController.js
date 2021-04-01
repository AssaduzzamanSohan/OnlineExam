var loanPanel = null;
Ext.define('Desktop.view.question.QuestionController', {
	extend: 'Ext.app.ViewController',
	alias: 'controller.questionController',

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

		if (headerRef == 'onClickSaveOptionBtn') {
			Ext.MessageBox.alert('STATUS', 'Data Successfully Saved');
			controller.onClickResetOptionBtn();
		} 
		if (headerRef == 'loadQuiestionViewGridData') {
			loadDataInGlobalStore(data, 'gQuestionStore');
		}
		if (headerRef == 'onSavePrepareExam') {
			controller.getView().close();
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

	onActivateQuestionPanelRender: function (cmp, eOpts) {

		setPluginWithoutListener(cmp.lookupReference('addNewQuestionGrid'));
		setPluginWithoutListener(cmp.lookupReference('prepareExamGrid'));

		var store = getGlobalStore('gOptionStoreToSave');
		store.insert(store.data.items.length, new Desktop.model.Option());

		this.loadQuiestionViewGridData(this);			
	},

	loadQuiestionViewGridData: function(cmp){
		var header = {
			reference: 'loadQuiestionViewGridData'
		};

		this.sendRequest('SELECT_ALL', 'Question', null, header);
	},
	onClickResetOptionBtn: function(){
		this.lookupReference('questionInput').reset();
		this.lookupReference('questionMark').reset();

		var store = getGlobalStore('gOptionStoreToSave');
		store.removeAll();
		store.insert(0, new Desktop.model.Option());
	},
	onNewOption: function(grid, rowIndex, colIndex){
		var store = getGlobalStore('gOptionStoreToSave');
		store.insert(store.data.items.length, new Desktop.model.Option());
	},
	onDelOption: function(grid, rowIndex, colIndex){
		var store = getGlobalStore('gOptionStoreToSave');
		store.removeAt(rowIndex);
	},	
	onDelFromExam: function(grid, rowIndex, colIndex){
		var store = getGlobalStore('gExamQuestionStore');
		store.removeAt(rowIndex);
	},
	onClickSaveOptionBtn: function(){
		console.log(this);

		var store = getGlobalStore('gOptionStoreToSave');
		var optionList = new Array();
		var correctAnsIdx = -1;
		var selected = this.lookupReference('addNewQuestionGrid').getSelectionModel().getSelected().items;
		if(selected.length>0){
			correctAnsIdx = store.find('id', selected[0].id);
		}
		for (var i = 0; i < store.data.items.length; i++) {
			if(i == correctAnsIdx) store.data.items[i].data.correctAns  = true;
			else{
				if(i == correctAnsIdx) store.data.items[i].data.correctAns  = false;	
			}
			optionList.push(store.data.items[i].data);
		}
		
		var payload = {
			question: this.lookupReference('questionInput').value,
			mark: this.lookupReference('questionMark').value,
			optionList: optionList
		};
		var header = {
			reference: 'onClickSaveOptionBtn'
		};
		showProcessMessage('Saving data....');
		this.sendRequest('NEW', 'Question', JSON.stringify(payload), header);
	},
	onRefreshQuestionViewGrid: function(cmp){
		this.loadQuiestionViewGridData(this);
	},
	onQuestionViewGridItemSelect: function(rowmodel, record, index){
		loadDataInGlobalStore(record[0].data.optionList, 'gOptionStore');
	},
	onClickQusAddToExamBtn: function(cmp){
		console.log('adding');
		var grid = this.lookupReference('questionSearchGrid');
		var selected = grid.getSelectionModel().getSelected().items;

		var store = getGlobalStore('gExamQuestionStore');

		for(var i=0; i<selected.length; i++){
			var examQuestionModel = new Desktop.model.ExamQuestion();
			examQuestionModel.data.mark = selected[i].data.mark;
			examQuestionModel.data.question = selected[i].data.question;
			examQuestionModel.data.questionKey = selected[i].data.questionKey;
			var optionList = selected[i].data.optionList;
			for(var j=0; j<optionList.length; j++){
				if(optionList[j].correctAns){
					examQuestionModel.data.answerOptionKey = optionList[j].optionKey;
					examQuestionModel.data.answer = optionList[j].option;
				}
			}
			store.insert(store.data.items.length, examQuestionModel);
		}

		Ext.MessageBox.alert('STSTUS', 'Added Into Prepare Exam List');
	},
	onClickSaveExamBtn: function(){
		console.log(this);
		var saveExamPopup = Ext.create('Desktop.view.question.PrepareExamPopup');
		saveExamPopup.show();
	},
	onActivatePrepareExamPopup: function(){
		console.log('onActivatePrepareExamPopup');

		var items = getGlobalStore('gExamQuestionStore').data.items;

		var totalTime = 0;
		for(var i=0; i<items.length; i++){
			totalTime = totalTime + items[i].data.timeInSecond;
		}

		this.lookupReference('totalTimeInMin').setValue(totalTime/60);
	},	
	onSavePrepareExam: function(){
		console.log('onSavePrepareExam');

		var examQuestionList = new Array();
		var items = getGlobalStore('gExamQuestionStore').data.items;
		for(var i=0; i<items.length; i++){
			examQuestionList.push(items[i].data);
		}
		var payload = {
			examinerKey: gExaminer.examinerKey,
			examType: this.lookupReference('examTitle').value,
			totalTimeInMin: this.lookupReference('totalTimeInMin').value,
			doNegativeMarking: this.lookupReference('negativeMark').value,
			negativeMark: this.lookupReference('negativeMarkValue').value,
			useEachQusTime: this.lookupReference('useEachQusTime').value,
			examQuestionList: examQuestionList
		}

		var header = {
			reference: 'onSavePrepareExam'
		}

		this.sendRequest('NEW', 'Exam', JSON.stringify(payload), header);
	}
});