/*!
 * Ext JS Library
 * Copyright(c) 2006-2014 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Desktop.ExamWindow', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'exam-win',

    init : function(){
        this.launcher = {
            text: 'exam',
            iconCls:'icon-exam-grid'
        };
        this.createGlobalStores();
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('exam-win');
        if(loginUser == null ||  loginUser == '') {
            loginUser=gLoginUuser;
        }
        else{
            loginUser=loginUser;
        }
        if(!win){
            win = desktop.createWindow({
                id: 'exam-win',
                title:'exam - ' + loginUser.firstName + ' ' + loginUser.lastName,
                width: desktop.getWidth() - 200,
                height: desktop.getHeight() - 50,
                iconCls: 'icon-exam-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        xtype: 'examPanel'
                    }
                ]
            });
        }
        return win;
    },

    createGlobalStores : function(){

        Ext.create('Ext.data.Store', {
            model: 'Desktop.model.Exam',
            storeId: 'gExamStore'
        });
    }
});

