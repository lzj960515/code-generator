package cn.zijiancode.code.generator.window;

import cn.zijiancode.code.generator.core.CodeGenerator;

import javax.swing.*;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
public class MybatisWhereGenerator {
    private JPanel rootPanel;
    private JTextField prefixText;
    private JTextArea statementText;
    private JButton generateButton;
    private JTextField paramName;
    private JRadioButton toUnderscore;
    private JComboBox<String> mode;
    private static final int WHERE = 0;
    private static final int COLUMN = 1;

    public MybatisWhereGenerator(){
        init();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void init(){
        mode.addActionListener((e) -> {
            paramName.setEnabled(mode.getSelectedIndex() == WHERE);
        });
        statementText.setText(CodeGenerator.generateWhereStatement(null, null, true));
        generateButton.addActionListener(e -> {
            if(mode.getSelectedIndex() == WHERE){
                statementText.setText(CodeGenerator.generateWhereStatement(paramName.getText(), prefixText.getText(), toUnderscore.isSelected()));
            }else {
                statementText.setText(CodeGenerator.generateColumn(prefixText.getText(), toUnderscore.isSelected()));
            }
        });
    }
}
