
Ext.define("Desktop.view.login.Login",{
    extend: "Ext.window.Window",

    requires: [
        "Desktop.view.login.LoginController",
        "Desktop.view.login.LoginModel",
        'appConstants',
        'Ext.ux.ActivityMonitor'
    ],

    controller: "login-login",
    viewModel: {
        type: "login-login"
    },

    //height: 650,
    //style: '{background-color:#ECEDEF;}',
    width: 360,
    closable: false,
    title   : 'Login As',
    closable: false,
    draggable: false,
    resizable: false,
    y: 170,
    iconCls:'icon-lock',

    layout: {
        type: 'hbox',
        align: 'middle',
        pack: 'center'
    },
    items: [
        {
            xtype: 'form',
            reference: 'loginFormRef',
            flex: 1,
            height: 120,
            maxWidth: 370,
            width: 370,
            layout: 'border',
            bodyBorder: true,
            items: [
                {
                    xtype: 'panel',
                    reference: 'loginPanelRef',
                    id:'loginPanel',
                    region: 'center',
                    margin: '5 5 5 5',
                    maxWidth: 360,
                    layout: 'form',
                    bodyStyle: "background-image:url('resources/images/desktop.gif')",
                    bind: {
                        reference: '{loginStore}'
                    },
                    items: [
                        {
                            xtype: 'tbspacer',
                            height: 15
                        },
                        {
                            reference: 'email',
                            xtype: 'textfield',
                            fieldLabel: 'Email',
                            dataIndex: 'emailId',
                            labelAlign: 'right',
                            labelStyle  : 'color: #fff;',
                            labelWidth: 80,
                            align: 'right',
                            value: 'email@gmai.com'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'bottom',
                            padding: 1,
                            items: [
                                '->',
                                {
                                    xtype: 'button',
                                    reference: 'loginBtnExamer',
                                    icon: 'admin.png',
                                    style: 'border: groove',
                                    text    : 'Examer',
                                    listeners: {
                                        click: 'onLoginButtonClick'
                                    }
                                },
                                {
                                    xtype: 'button',
                                    reference: 'loginBtnExaminer',
                                    icon: 'admin.png',
                                    style: 'border: groove',
                                    text    : 'Examiner',
                                    listeners: {
                                        click: 'onLoginButtonClick'
                                    }
                                },
                                {
                                    xtype: 'tbspacer',
                                    width: 7
                                },
                                {
                                    xtype: 'button',
                                    style: 'border: groove',
                                    icon: 'login-cancel.png',
                                    text    : 'Cancel',
                                    listeners: {
                                        click: 'onCancelButtonClick'
                                    }
                                },
                                {
                                    xtype: 'tbspacer',
                                    width: 7
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]
});
