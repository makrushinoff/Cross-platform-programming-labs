#include "NumberHandler.h"
#include <stdio.h>
#include <string.h>

/*
 * Class:     NumberHandler
 * Method:    powIntTo2
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_NumberHandler_powIntTo2
  (JNIEnv *env, jclass claz, jint value) {
    return (value * value);
  }

/*
 * Class:     NumberHandler
 * Method:    divideBy2Double
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_NumberHandler_divideBy2Double
  (JNIEnv *env, jclass claz, jdouble value) {
    return value / (jdouble)2;
  }

/*
 * Class:     NumberHandler
 * Method:    substract10fromFloat
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_NumberHandler_subtract10fromFloat
   (JNIEnv *env, jclass claz, jfloat value) {
    return value - 10.0;
  }

/*
 * Class:     NumberHandler
 * Method:    addAuthorToString
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_NumberHandler_addAuthorToString
  (JNIEnv *env, jclass claz, jstring str) {

    char *concatenated;
    const jbyte *sx;
    jstring retval;

    sx = (*env)->GetStringUTFChars(env, str, NULL);

    concatenated = malloc(strlen("Andrii Makrushyn_") + strlen(sx) + 1);
    strcpy(concatenated, "Andrii Makrushyn_");
    strcat(concatenated, sx);

    retval = (*env)->NewStringUTF(env, concatenated);

    (*env)->ReleaseStringUTFChars(env, str, sx);

    free(concatenated);

    return retval;

  }