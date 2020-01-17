package de.fhg.iais.roberta.worker.compile;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.iais.roberta.bean.CompilerSetupBean;
import de.fhg.iais.roberta.codegen.AbstractCompilerWorkflow;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.util.Key;
import de.fhg.iais.roberta.util.Pair;
import de.fhg.iais.roberta.util.Util;
import de.fhg.iais.roberta.worker.IWorker;

public class GccCompilerWorker implements IWorker {

    private static final Logger LOG = LoggerFactory.getLogger(GccCompilerWorker.class);

    @Override
    public void execute(Project project) {
        CompilerSetupBean compilerWorkflowBean = (CompilerSetupBean) project.getWorkerResult("CompilerSetup");
        final String compilerResourcesDir = compilerWorkflowBean.getCompilerResourcesDir();
        final String tempDir = compilerWorkflowBean.getTempDir();
        String programName = project.getProgramName();
        String token = project.getToken();
        Util.storeGeneratedProgram(tempDir, project.getSourceCode().toString(), token, programName, "." + project.getSourceCodeFileExtension());
        String scriptName = compilerResourcesDir + "arduino-resources/build_project.sh";
        String userProgramDirPath = tempDir + token + "/" + programName;

        String boardVariant;
        String mmcu;
        if ( project.getRobot().equals("uno") || project.getRobot().equals("nano") ) {
            boardVariant = "standard";
            mmcu = "atmega328p";
        } else {
            boardVariant = "mega";
            mmcu = "atmega2560";
        }

        String arduinoVariant = "ARDUINO_AVR_" + project.getRobot().toUpperCase();
        String buildDir = tempDir + token + "/" + programName + "/source";
        String arduinoDirName = project.getRobot();

        String[] executableWithParameters =
            {
                scriptName,
                boardVariant,
                mmcu,
                arduinoVariant,
                buildDir,
                programName,
                compilerResourcesDir,
                arduinoDirName
            };
        Pair<Boolean, String> result = AbstractCompilerWorkflow.runCrossCompiler(executableWithParameters);
        Key resultKey = result.getFirst() ? Key.COMPILERWORKFLOW_SUCCESS : Key.COMPILERWORKFLOW_ERROR_PROGRAM_COMPILE_FAILED;
        if ( result.getFirst() ) {
            String base64EncodedHex =
                AbstractCompilerWorkflow.getBase64EncodedHex(Paths.get(userProgramDirPath) + "/target/" + programName + "." + project.getBinaryFileExtension());
            project.setCompiledHex(base64EncodedHex);
            if ( project.getCompiledHex() != null ) {
                resultKey = Key.COMPILERWORKFLOW_SUCCESS;
            } else {
                resultKey = Key.COMPILERWORKFLOW_ERROR_PROGRAM_COMPILE_FAILED;
            }
        }
        project.setResult(resultKey);
        project.addResultParam("MESSAGE", result.getSecond());
        String robot = project.getRobot();
        if ( resultKey == Key.COMPILERWORKFLOW_SUCCESS ) {
            LOG.info("compile {} program {} successful", robot, programName);
        } else {
            LOG.error("compile {} program {} failed with {}", robot, programName, result.getSecond());
        }
    }
}
