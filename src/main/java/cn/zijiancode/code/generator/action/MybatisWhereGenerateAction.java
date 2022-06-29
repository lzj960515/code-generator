package cn.zijiancode.code.generator.action;

import cn.zijiancode.code.generator.core.JavaClassParser;
import cn.zijiancode.code.generator.util.StringUtils;
import cn.zijiancode.code.generator.window.MybatisWhereGenerator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import io.netty.util.internal.StringUtil;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
public class MybatisWhereGenerateAction extends AnAction {

    private static final String JAVA = "JAVA";

    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取当前编辑器对象
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        VirtualFile virtualFile = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile();
        if (JAVA.equals(virtualFile.getFileType().getName())) {
            //获取选择的数据模型
            SelectionModel selectionModel = editor.getSelectionModel();
            //获取当前选择的文本
            String selectedText = selectionModel.getSelectedText();
            if (StringUtils.isBlank(selectedText)) {
                Document document = editor.getDocument();
                JavaClassParser.parseField(document.getText());
            } else {
                JavaClassParser.parseField(selectedText);
            }
            showWhereGenerateWindow(e.getProject());
        }
    }

    private void showWhereGenerateWindow(Project project) {
        MybatisWhereGenerator mybatisWhereGenerator = new MybatisWhereGenerator();
        DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setCenterPanel(mybatisWhereGenerator.getRootPanel());
        dialogBuilder.setTitle("Where语句生成结果");
        dialogBuilder.setOkOperation(() -> {
            JavaClassParser.clear();
            dialogBuilder.getDialogWrapper().close(0);
        });
        dialogBuilder.setCancelOperation(() -> {
            JavaClassParser.clear();
            dialogBuilder.getDialogWrapper().close(0);
        });
        dialogBuilder.show();

    }
}
