package cn.zijiancode.code.generator.window;

import cn.zijiancode.code.generator.core.WhereStatementGenerator;

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

    public MybatisWhereGenerator(){
        init();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void init(){
        statementText.setText(WhereStatementGenerator.generateWhereStatement(null));
        generateButton.addActionListener(e -> {
            statementText.setText(WhereStatementGenerator.generateWhereStatement(prefixText.getText()));
        });
    }
}
