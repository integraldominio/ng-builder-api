
/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.idomine.domain.crud.service.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CompactadorHelper
{

    // Constantes
    static final int TAMANHO_BUFFER = 4096; // 4kb

    // método para compactar arquivo
    public static void compactarParaZip(String arqSaida, String arqEntrada)
            throws IOException
    {
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];

        BufferedInputStream origem = null;
        FileInputStream streamDeEntrada = null;
        FileOutputStream destino = null;
        ZipOutputStream saida = null;
        ZipEntry entry = null;

        try
        {
            destino = new FileOutputStream(new File(arqSaida));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));
            File file = new File(arqEntrada);
            streamDeEntrada = new FileInputStream(file);
            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1)
            {
                saida.write(dados, 0, cont);
            }
            origem.close();
            saida.close();
        }
        catch (IOException e)
        {
            throw new IOException(e.getMessage());
        }
    }

    public static void compactarPastaParaZip(String arquivoZipado, String pastaOrigem)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(arquivoZipado);

            ZipOutputStream zipOut = new ZipOutputStream(fos);

            File pasta = new File(pastaOrigem);
            for (File arq : pasta.listFiles())
            {
                zipOut.putNextEntry(new ZipEntry(arq.getName().toString()));
                FileInputStream fis = new FileInputStream(arq);
                int content;
                while ((content = fis.read()) != -1)
                {
                    zipOut.write(content);
                }
                fis.close();
                zipOut.closeEntry();
            }

            zipOut.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void lerArquivoZipado()
    {
        try
        {
            FileInputStream fis = new FileInputStream("arquivo.zip");
            ZipInputStream zipIn = new ZipInputStream(fis);
            ZipEntry entry = zipIn.getNextEntry();

            while (zipIn.available() > 0)
            {
                System.out.print((char) zipIn.read());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
